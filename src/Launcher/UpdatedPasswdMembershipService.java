/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Launcher;

import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import net.jxta.credential.AuthenticationCredential;
import net.jxta.credential.Credential;
import net.jxta.document.Advertisement;
import net.jxta.document.Element;
import net.jxta.document.MimeMediaType;
import net.jxta.document.StructuredDocument;
import net.jxta.document.StructuredDocumentFactory;
import net.jxta.document.StructuredTextDocument;
import net.jxta.document.TextElement;
import net.jxta.exception.JxtaError;
import net.jxta.exception.PeerGroupException;
import net.jxta.exception.ProtocolNotSupportedException;
import net.jxta.id.ID;
import net.jxta.id.IDFactory;
import net.jxta.impl.membership.NullMembershipService;

import net.jxta.membership.*;
import net.jxta.peer.PeerID;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupID;
import net.jxta.platform.ModuleSpecID;
import net.jxta.protocol.ModuleImplAdvertisement;
import net.jxta.protocol.PeerGroupAdvertisement;
import net.jxta.service.Service;
import org.apache.log4j.Category;

/**
 *
 * @author aniss
 */
public class UpdatedPasswdMembershipService implements MembershipService{

    
    private static final Category LOG = Category.getInstance(UpdatedPasswdMembershipService.class.getName());
    public UpdatedPasswdMembershipService()throws Exception{
        
    }

    @Override
    public Credential getDefaultCredential() throws PeerGroupException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Enumeration<Credential> getCurrentCredentials() throws PeerGroupException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Enumeration<AuthenticationCredential> getAuthCredentials() throws PeerGroupException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPropertyChangeListener(String string, PropertyChangeListener pl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removePropertyChangeListener(String string, PropertyChangeListener pl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class PasswdCredential implements Credential{
        UpdatedPasswdMembershipService source;
        String whoami;
        
        ID peerID;
        String signedPeerID;
        
        protected PasswdCredential(UpdatedPasswdMembershipService source, String whoami, String signedPeerID) {
            this.source = (UpdatedPasswdMembershipService) source;
            this.whoami = whoami;
            this.signedPeerID = signedPeerID;
            this.peerID = source.getPeerGroup().getPeerID();
            
            
        }
        
        protected PasswdCredential(UpdatedPasswdMembershipService source, PeerGroupID peergroup, PeerID peer, String whoami, String signedPeerID) throws PeerGroupException{
            
            this.source = (UpdatedPasswdMembershipService) source;
            
            if(!source.getPeerGroup().getPeerGroupID().equals(peergroup))
                throw new PeerGroupException("Cannot credential for a different peer group");
            
            this.whoami = whoami;
            this.peerID = peer;
            this.signedPeerID = signedPeerID;
            
            
            
            
        }
        

               public MembershipService getSourceService(){
           return source;
       }
        
                public ID getPeerGroupID(){
            return source.getPeerGroup().getPeerGroupID();
        }
        
                public ID getPeerID(){
            return peerID;
        }
        
                public StructuredDocument getDocument(MimeMediaType as) throws Exception{
            StructuredDocument doc = StructuredDocumentFactory.newStructuredDocument(as,"PasswdCredential");
            
            Element e = doc.createElement("PeerGroupID", peergroup.getPeerGroupID().toString());
            
            doc.appendChild(e);
            
            e = doc.createElement("PeerID", peergroup.getPeerID().toString());
            doc.appendChild(e);
            
            e = doc.createElement("Identity", whoami);
            doc.appendChild(e);
            
            e = doc.createElement("ReallyInsecureSignature", signedPeerID);
            doc.appendChild(e);
            
            return doc;
            
        }
        
        
        public String getIdentity(){
            return whoami;
        }

        @Override
        public boolean isExpired() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isValid() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object getSubject() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

        
        public class PasswdAuthenticator implements Authenticator{
            
            MembershipService source;
            AuthenticationCredential application;
            String whoami = null;
            String password = null;

            public PasswdAuthenticator(MembershipService source, AuthenticationCredential application) {
                
                this.source = source;
                this.application = application;
                
                try{
                    StructuredTextDocument credentialsDoc = (StructuredTextDocument) application.getDocument(new MimeMediaType("text/xml"));
                    Enumeration elements = credentialsDoc.getChildren("IdentityInfo");
                    elements = ((TextElement) elements.nextElement()).getChildren();
                    elements = ((TextElement) elements.nextElement()).getChildren();
                    
                    while(elements.hasMoreElements()){
                        TextElement elem = (TextElement) elements.nextElement();
                        
                        String nm = elem.getName();
                        if(nm.equals("login")){
                            whoami = elem.getTextValue();
                            continue;
                        }
                        if(nm.equals("password")){
                            password = elem.getTextValue();
                            continue;
                        }
                    }
                    
                }catch(Exception e){
                    
                    e.printStackTrace();
                    System.exit(-1);
                    
                }
            }
            
            
            @Override
            public MembershipService getSourceService(){
                return source;
            }
            @Override
            synchronized public boolean isReadyForJoin(){
                return ((null != password) && (null !=  whoami));
            }
            
            @Override
            public String getMethodName(){
                return "PasswdAuthentication";
            }
            
            public void setAuth1Identity(String who){
                whoami = who;
            }
            
            public String getAuth1Identity(){
                return whoami;
            }
            
            public void setAuth2_Password(String secret){
                password = secret;
            }
            
            private String getAuth2_Password(){
                return password;
            }
            
            @Override
            public AuthenticationCredential getAuthenticationCredential(){
                return application;
            }
            
        }
    
    
        
        
        static class IdMaker{
            static ID mkID(String s){
                try{
                    return IDFactory.fromURL(new URL("urn", "", "jxta:uuid-" + s));
                }catch(MalformedURLException absurd){}catch(UnknownServiceException absurd2){}
                throw new JxtaError("Hardcoded Spec and Class IDs are malformed");
            }
        }
        
        public static final ModuleSpecID passwordMembershipSpecID = (ModuleSpecID) IdMaker.mkID("DeadBeefDeafBabaFeedBabe00000005" + "02" + "06");
        
        private PeerGroup peergroup = null;
        
        private Vector principals = null;
        
        private Vector authCredentials = null;
        
        private ModuleImplAdvertisement implAdvertisement = null;
        
        private Hashtable logins = null;
        
    @Override
        public void init(PeerGroup group ,ID assignedID, Advertisement impl)throws PeerGroupException{
        
        peergroup = group;
        implAdvertisement = (ModuleImplAdvertisement) impl;
        PeerGroupAdvertisement configAdv = (PeerGroupAdvertisement) group.getPeerGroupAdvertisement();
        
        resign();
            
        }
    
    @Override
    public Service getInterface(){
        return this;
    }
    
    @Override
    public Advertisement getImplAdvertisement(){
        return implAdvertisement;
    }
    
    @Override
    public int startApp(String [] arg){
        return 0;
    }
    
    @Override
    public void stopApp(){
        
    }
    
    
    public PeerGroup getPeerGroup(){
        return peergroup;
    }
    
    @Override
    public Authenticator apply(AuthenticationCredential application)throws PeerGroupException,ProtocolNotSupportedException{
        String method = application.getMethod();
        
        if((null != method) && !"UpdatedPasswdAuthentication".equals(method))throw new ProtocolNotSupportedException("Authentication method not recognized");
        return new PasswdAuthenticator(this,application);
    }
    
    
  
    /*@Override
    public synchronized Enumeration getCurrentCredentials() throws PeerGroupException{
        return principals.elements();
    }*/
    
    @Override
    public synchronized Credential join(Authenticator authenticated)throws PeerGroupException{
        if(!(authenticated instanceof PasswdAuthenticator)) throw new ClassCastException("This is not my authenticator !");
        if(!authenticated.isReadyForJoin()) throw new PeerGroupException("Not ready to join");
        if( !checkPasswd(
                ((PasswdAuthenticator)authenticated).getAuth1Identity(),
                ((PasswdAuthenticator)authenticated).getAuth2_Password())) 
                        throw new PeerGroupException("Incorrect password !");
        
        Credential newCred = new PasswdCredential(this, ((PasswdAuthenticator)authenticated).getAuth1Identity(), "blah");
        
        principals.addElement(newCred);
        authCredentials.addElement(authenticated.getAuthenticationCredential());
        
        return newCred;
    }
    
    @Override
    public synchronized void resign() throws PeerGroupException{
        principals = new Vector();
        authCredentials = new Vector();
        
        principals.addElement(new PasswdCredential(this, "nobody", "blah"));
    }
    
    @Override
    public Credential makeCredential(Element element) throws PeerGroupException,Exception{
        Object rootIs = element.getKey();
        if(!"PasswdCredential".equals(rootIs)) throw new PeerGroupException("Element does not contain a recognized credential format");
        Enumeration children = element.getChildren("PeerGroupID");
        
        if(!children.hasMoreElements()) throw  new RuntimeException("Missing PeerGoupID element");
        PeerGroupID peergroup = (PeerGroupID) IDFactory.fromURL(new URL((String)((Element) children.nextElement()).getValue()));
        if(children.hasMoreElements()) throw new RuntimeException("Extra PeerGroupID elemnts");
        children = element.getChildren("PeerID");
        if(!children.hasMoreElements()) throw  new RuntimeException("Missing PeerID element");
        PeerID peer = (PeerID) IDFactory.fromURL(new URL((String) ((Element) children.nextElement()).getValue()));
        if(children.hasMoreElements()) throw  new RuntimeException("Extra PeerID elemnts");
        children = element.getChildren("Identity");
        if(!children.hasMoreElements()) throw  new RuntimeException("Missing PeerID element");
        String whoami = (String) ((Element) children.nextElement()).getValue();
        if(children.hasMoreElements()) throw  new RuntimeException("Extra Identity elements");
        children = element.getChildren("ReallyInsecureSignature");
        if(!children.hasMoreElements()) throw  new RuntimeException("Missing 'ReallyInsecureSignature' element");
        String signedPeerID = (String) ((Element) children.nextElement()).getValue();
        if(children.hasMoreElements()) throw  new RuntimeException("Extra 'ReallyInsecureSignature' elements");
        return new PasswdCredential(this, peergroup, peer, whoami, signedPeerID);
    }
    
    private boolean checkPasswd(String identity, String passwd){
        boolean result;
        
        result = passwd.equals(makePsswd("password"));
        
        return result;
        
    }
    
    public static String makePsswd(String source){
        final String xlateTable = "DQKWHRTENOGXCVYSFJPILZABMU";
        StringBuffer work = new StringBuffer(source);
        
        for(int eachChar = work.length() - 1; eachChar >= 0; eachChar --){
            char aChar = Character.toUpperCase(work.charAt(eachChar));
            
            int replaceIdx = xlateTable.indexOf(aChar);
            if(-1 != replaceIdx)
                work.setCharAt(eachChar, (char) ('A' + replaceIdx));
        }
        
        return work.toString();
    }
        
        
        
        
        
        
       
        
        
        
        
        
        

        

        

        
        
    }
    
    

