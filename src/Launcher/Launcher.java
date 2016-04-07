/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Launcher;

import static Launcher.Launcher.deleteDirectory;
import static Launcher.Launcher.frame_set;
import Master.MasterPeer;
import Worker.WorkerPeer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import net.jxta.credential.AuthenticationCredential;
import net.jxta.discovery.DiscoveryEvent;
import net.jxta.discovery.DiscoveryListener;
import net.jxta.discovery.DiscoveryService;
import net.jxta.document.AdvertisementFactory;
import net.jxta.document.MimeMediaType;
import net.jxta.document.StructuredDocument;
import net.jxta.document.StructuredDocumentFactory;
import net.jxta.document.StructuredTextDocument;
import net.jxta.document.TextElement;
import net.jxta.exception.PeerGroupException;
import net.jxta.exception.ProtocolNotSupportedException;
import net.jxta.membership.Authenticator;
import net.jxta.membership.MembershipService;
import net.jxta.peer.PeerID;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupID;
import net.jxta.pipe.InputPipe;
import net.jxta.pipe.PipeService;
import net.jxta.platform.NetworkConfigurator;
import net.jxta.platform.NetworkManager;
import net.jxta.protocol.ModuleImplAdvertisement;
import net.jxta.protocol.PeerGroupAdvertisement;
import net.jxta.protocol.PipeAdvertisement;
import net.jxta.rendezvous.RendezvousEvent;
import net.jxta.rendezvous.RendezvousListener;
import net.jxta.id.IDFactory;
import net.jxta.platform.JxtaLoader;
import net.jxta.platform.ModuleSpecID;
import net.jxta.protocol.DiscoveryResponseMsg;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Abdelhakim
 */
public class Launcher extends JFrame implements ActionListener, Runnable, RendezvousListener {

    public static Frame_set frame_set;
    int iterationCount = 19;
    private String secretKey = "0551468062";
    public String str;
    public String chaine = null;
    private PeerGroup netPeerGroup,Aniss_Hakim_PeerGroup = null,discovered_Aniss_Hakim_PeerGroup = null;
    private DiscoveryService myDiscoveryService = null;
    private PipeAdvertisement pipeAdvertisement;
    private InputPipe myInputPipe;
    private final static String SenderMessage = "pipe_tutorial";
    private ArrayList al = new ArrayList();
    private int x1 = 0;
    private int y = 0;
    private int z = 0;
    private Cipher ecipher;
    public String k;
    public PeerID pid;
    private LinkedList list = new LinkedList();
    public String PipeType;
    private String Name;
    private String Password;
    private  PeerID peerID;
    private static final String netPeerGroupName = "Default_PeerGroup";
    private static final String Aniss_Hakim_PeerGroupName = "Aniss_Hakim_PeerGroup";
    
    public File jxtaPath;
    public PeerGroup peerGroup;
    public static final String ROUTEADV = "ROUTE";
    private PipeService myPipeService;
    PeerGroupID NetPeerGroupID = null;
    private PeerGroupID Aniss_HakimPeerGroupID;
    private final static MimeMediaType XMLMIMETYPE = new MimeMediaType("text/xml");
    
    NetworkManager TheNetworkManager = null;
    NetworkConfigurator TheConfig = null;
    private JPanel container = new JPanel();
    private JRadioButton jRadioButton1 = new JRadioButton("RDV");
    private JRadioButton jRadioButton2 = new JRadioButton("EDGE");
    private int port;

    public static void main(String args[]) {
        Launcher launcher = new Launcher();
        launcher.run();
    }

    static public boolean deleteDirectory(File path) {
        boolean resultat = true;

        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    resultat &= deleteDirectory(files[i]);
                } else {
                    resultat &= files[i].delete();
                }
            }
        }
        resultat &= path.delete();
        return (resultat);
    }
    public void set_Frame_set(Frame_set y){
        frame_set = y;
    }
    
     public void set_Edge() {

        
//String name = System.getProperty ( "os.name" );

        frame_set.setVisible(false);
         /*if(System.getProperty("os.name").contains("Windows")){
            
            getPath(new File("./"),".jxta");
            
        }else{
            if(System.getProperty("os.name").contains("Linux")){
                System.out.println("hello linux!!!");
                getPath(new File("/home/"),"Matrix.txt");
            }
        }
         if(!al.isEmpty()){
             jxtaPath = new File(al.get(0).toString());
         
        
        boolean supprimer = deleteDirectory(jxtaPath);
        if(supprimer){
            System.out.println("Suppression réussie");
        }
             
         }*/
         



       Logger.getLogger("net.jxta").setLevel(Level.OFF);

        String ip = null;

        try {
            
            
                    TheNetworkManager = new NetworkManager(NetworkManager.ConfigMode.EDGE, netPeerGroupName);
        

         
          
            System.out.println("Retrieving the Network Configurator");

            

            TheConfig = TheNetworkManager.getConfigurator();
            
           
                
            if(!TheConfig.exists()){
                
                System.out.println("No local configuration found");
                
                
                  Object[] message = new Object[ 6 ];
                    message[ 0 ] = "login :";
                    message[ 1 ] = new JTextField();
                    message[ 2 ] = "mot de passe";
                    message[ 3 ] = new JPasswordField();
                     message[ 4 ] = "Port";
                     message[ 5 ] = new JTextField();
 
                    String option[] = { "OK", "Annuler" };
 
                int result = JOptionPane.showOptionDialog(null,message,"connexion",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,message[1] );
 
                if( result == 0 )
                {
  
                    Name = ( (JTextField)message[ 1 ] ).getText() ;
                    Password=  new String( ( (JPasswordField)message[ 3 ] ).getPassword() ) ;
                     port = Integer.parseInt(((JTextField)message[ 5 ]).getText());
  
                }else{
                    System.exit(0);
                }



                
                  NetPeerGroupID = (PeerGroupID) IDFactory.fromURI(new URI("urn:jxta:uuid-8B33E012995678918BF9A446A224B16F02"));

            

        

// Persisting it to make sure the Peer ID is not re-created each
// time the Network Manager is instantiated
            TheNetworkManager.setConfigPersistent(true);
            TheNetworkManager.setUseDefaultSeeds(false);
            TheNetworkManager.setInfrastructureID(NetPeerGroupID);
            
             peerID = IDFactory.newPeerID(PeerGroupID.worldPeerGroupID, Name.getBytes());
            
            TheConfig.setName(Name);

            TheConfig.setPrincipal(Name);
            TheConfig.setPassword(Password);
            TheConfig.setDescription("Jxta_Aniss_Hakim Config");

//String seed = p.getUserName() + SEED;

            TheConfig.setInfrastructureName(netPeerGroupName);




            InetAddress address;
            address = InetAddress.getLocalHost();
            TheConfig.setTcpPublicAddress(address.getHostAddress(), true);
             

// TheConfig.addRdvSeedingURI(seedingURI);
// TheConfig.addRelaySeedingURI(seedingURI);
//TheConfig.setRendezvousSeedingURIs((p.getRend()));
//TheConfig.setRelaySeedingURIs((p.getRelay()));
//TheConfig.setTcpPublicAddress(p.getRelay(),true);

            TheConfig.setTcpPort(port);
            TheConfig.setPeerID(peerID);
            TheConfig.setTcpEnabled(true);
            TheConfig.setTcpIncoming(true);
            TheConfig.setTcpOutgoing(true);
            TheConfig.setUseMulticast(true);
            TheConfig.setTcpEndPort(-1);
            TheConfig.setTcpStartPort(-1);





            System.out.println("Saving new configuration");
            TheConfig.save();
            System.out.println("New configuration saved successfully");






            netPeerGroup = TheNetworkManager.startNetwork();

           myDiscoveryService = netPeerGroup.getDiscoveryService();
           Aniss_HakimPeerGroupID = createPeerGroupID("jxta:uuid-DCEF4386EAED4908BE25CE5019EA02");
                
                
                Aniss_Hakim_PeerGroup = createPeerGroup(Aniss_HakimPeerGroupID, Aniss_Hakim_PeerGroupName,"Grid P2P");
                
                joinGroup(Aniss_Hakim_PeerGroup);
                
                

          
            Worker.WorkerPeer workerPeer = new WorkerPeer(Aniss_Hakim_PeerGroup, TheNetworkManager);
            workerPeer.start();

                
            


                
            }else{
                 Object[] message = new Object[ 4 ];
                    message[ 0 ] = "login :";
                    message[ 1 ] = new JTextField();
                    message[ 2 ] = "mot de passe";
                    message[ 3 ] = new JPasswordField();
                   
 
                    String option[] = { "OK", "Annuler" };
 
                int result = JOptionPane.showOptionDialog(null,message,"connexion",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,message[1] );
 
                if( result == 0 )
                {
  
                    Name = ( (JTextField)message[ 1 ] ).getText() ;
                    Password=  new String( ( (JPasswordField)message[ 3 ] ).getPassword() ) ;
                    
                    
  
                }else{
                    System.exit(0);
                }
                        netPeerGroup = TheNetworkManager.startNetwork();
                        myDiscoveryService = netPeerGroup.getDiscoveryService();
                        
                        
                        findAdvertisement("Name", Aniss_Hakim_PeerGroupName);
                
                        
                
                        
                        
                        System.out.println("Peer Name : "+Aniss_Hakim_PeerGroup.getPeerName());
               Enumeration enumeration1 = Aniss_Hakim_PeerGroup.getPeerGroupAdvertisement().getServiceParam(PeerGroup.membershipClassID).getChildren();
               while(enumeration1.hasMoreElements()){
                   
                    
                   
                  
                   
                   String recuperer = enumeration1.nextElement().toString();
                   
                   
                      System.out.println(recuperer);
                      
                      
                      if(recuperer.contains(Name)){
                          if(recuperer.contains(UpdatedPasswdMembershipService.makePsswd(Password))){
                              
                                   Worker.WorkerPeer workerPeer = new WorkerPeer(Aniss_Hakim_PeerGroup, TheNetworkManager);
                                    workerPeer.start();
                          
                            }else{
                              JOptionPane.showMessageDialog(this, "Mot de passe erroné","Erreur", JOptionPane.ERROR_MESSAGE);
                              System.exit(0);
                           }
                          
                      
                          
                      }else{
                          
                          JOptionPane.showMessageDialog(this, "Nom erroné","Erreur", JOptionPane.ERROR_MESSAGE);
                          System.exit(0);
                      }
                      
               
           }
               
                
            }    
                
                
                
                
            


                
            
            
            

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void set_Rendez_vous() {
        
        

        frame_set.setVisible(false);
        
        /*if(System.getProperty("os.name").contains("Windows")){
            
            getPath(new File("./"),".jxta");
            
        }else{
            if(System.getProperty("os.name").contains("Linux")){
                System.out.println("hello linux!!!");
                getPath(new File("/home/"),"Matrix.txt");
            }
        }
         if(!al.isEmpty()){
             jxtaPath = new File(al.get(0).toString());
         
        
        boolean supprimer = deleteDirectory(jxtaPath);
        if(supprimer){
            System.out.println("Suppression réussie");
        }
             
         }*/
        String ip = null;
        
        
        Logger.getLogger("net.jxta").setLevel(Level.OFF);

        try {
      
           

            
            
            
                 




        
            
            


            
           

            TheNetworkManager = new NetworkManager(NetworkManager.ConfigMode.PROXY,netPeerGroupName);
            
            

// Persisting it to make sure the Peer ID is not re-created each
// time the Network Manager is instantiated
            
            System.out.println("Retrieving the Network Configurator");


            TheConfig = TheNetworkManager.getConfigurator();

           if(!TheConfig.exists()){
               
               System.out.println("No local configuration found");
                
                 Object[] message = new Object[ 6 ];
                    message[ 0 ] = "login :";
                    message[ 1 ] = new JTextField();
                    message[ 2 ] = "mot de passe";
                    message[ 3 ] = new JPasswordField();
                    message[ 4 ] = "Port";
                     message[ 5 ] = new JTextField();
 
                    String option[] = { "OK", "Annuler" };
 
                int result = JOptionPane.showOptionDialog(null,message,"connexion",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,message[1] );
 
                if( result == 0 )
                {
  
                    Name = ( (JTextField)message[ 1 ] ).getText() ;
                    Password=  new String( ( (JPasswordField)message[ 3 ] ).getPassword() ) ;
                    port = Integer.parseInt(((JTextField)message[ 5 ]).getText());
                    
  
                }else{
                    System.exit(0);
                }

                
                NetPeerGroupID = (PeerGroupID) IDFactory.fromURI(new URI("urn:jxta:uuid-8B33E012995678918BF9A446A224B16F02"));

                TheNetworkManager.setConfigPersistent(true);
            TheNetworkManager.setUseDefaultSeeds(false);
            TheNetworkManager.setInfrastructureID(NetPeerGroupID);
             peerID = IDFactory.newPeerID(PeerGroupID.worldPeerGroupID, Name.getBytes());
                TheConfig.setName(Name);

            TheConfig.setPrincipal(Name);
            TheConfig.setPassword(Password);
            TheConfig.setDescription("Jxta_Aniss_Hakim Config");

//String seed = p.getUserName() + SEED;

            TheConfig.setInfrastructureName(netPeerGroupName);
            TheConfig.setInfrastructureID(NetPeerGroupID);



            InetAddress address;
            address = InetAddress.getLocalHost();
            TheConfig.setTcpPublicAddress(address.getHostAddress(), true);
             

// TheConfig.addRdvSeedingURI(seedingURI);
// TheConfig.addRelaySeedingURI(seedingURI);
//TheConfig.setRendezvousSeedingURIs((p.getRend()));
//TheConfig.setRelaySeedingURIs((p.getRelay()));
//TheConfig.setTcpPublicAddress(p.getRelay(),true);
           
            

            TheConfig.setTcpPort(port);
            TheConfig.setPeerID(peerID);
            TheConfig.setTcpEnabled(true);
            TheConfig.setTcpIncoming(true);
            TheConfig.setTcpOutgoing(true);
            TheConfig.setUseMulticast(true);
            TheConfig.setTcpEndPort(-1);
            TheConfig.setTcpStartPort(-1);
            TheConfig.setHttpEnabled(true);
            TheConfig.setHttpIncoming(true);
            TheConfig.setHttpOutgoing(true);
            



            System.out.println("Saving new configuration");
            TheConfig.save();
            System.out.println("New configuration saved successfully");




                netPeerGroup = TheNetworkManager.startNetwork();
                myDiscoveryService = netPeerGroup.getDiscoveryService();
                Aniss_HakimPeerGroupID = createPeerGroupID("jxta:uuid-DCEF4386EAED4908BE25CE5019EA02");
                
                
                Aniss_Hakim_PeerGroup = createPeerGroup(Aniss_HakimPeerGroupID, Aniss_Hakim_PeerGroupName,"Grid P2P");
               
                System.out.println(Aniss_Hakim_PeerGroup.getPeerGroupAdvertisement());
                
               
                joinGroup(Aniss_Hakim_PeerGroup);
               
                
                

               
            
               
            Master.MasterPeer masterPeer = new MasterPeer(Aniss_Hakim_PeerGroup, TheNetworkManager);

          
            
                
               
           }else{
               
                   
                   
                   Object[] message = new Object[ 4 ];
                    message[ 0 ] = "login :";
                    message[ 1 ] = new JTextField();
                    message[ 2 ] = "mot de passe";
                    message[ 3 ] = new JPasswordField();
                   
 
                    String option[] = { "OK", "Annuler" };
 
                int result = JOptionPane.showOptionDialog(null,message,"connexion",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,message[1] );
 
                if( result == 0 )
                {
  
                    Name = ( (JTextField)message[ 1 ] ).getText() ;
                    Password=  new String( ( (JPasswordField)message[ 3 ] ).getPassword() ) ;
                    
                    
  
                }else{
                    System.exit(0);
                }
                        netPeerGroup = TheNetworkManager.startNetwork();
                        myDiscoveryService = netPeerGroup.getDiscoveryService();
                        
                        
                        findAdvertisement("Name", Aniss_Hakim_PeerGroupName);
                
                        
                
                        
                        
                        System.out.println("Peer Name : "+Aniss_Hakim_PeerGroup.getPeerName());
               Enumeration enumeration1 = Aniss_Hakim_PeerGroup.getPeerGroupAdvertisement().getServiceParam(PeerGroup.membershipClassID).getChildren();
               while(enumeration1.hasMoreElements()){
                   
                    
                   
                  
                   
                   String recuperer = enumeration1.nextElement().toString();
                   
                   
                      System.out.println(recuperer);
                      
                      
                      if(recuperer.contains(Name)){
                          if(recuperer.contains(UpdatedPasswdMembershipService.makePsswd(Password))){
                              
                               Master.MasterPeer masterPeer = new MasterPeer(Aniss_Hakim_PeerGroup, TheNetworkManager);
                          
                            }else{
                              JOptionPane.showMessageDialog(this, "Mot de passe erroné","Erreur", JOptionPane.ERROR_MESSAGE);
                              System.exit(0);
                           }
                          
                      
                          
                      }else{
                          
                          JOptionPane.showMessageDialog(this, "Nom erroné","Erreur", JOptionPane.ERROR_MESSAGE);
                          System.exit(0);
                      }
                      
               
           }
               
               
           }
            
           
                
                
          
            
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

        if (source == jRadioButton1) {

            jRadioButton2.setEnabled(false);
            this.setVisible(false);
            // this.set_Rendez_vous();



        } else {
            if (source == jRadioButton2) {

                jRadioButton1.setEnabled(false);
                this.setVisible(false);
                this.set_Edge();

            }

        }

    }

    @Override
    public void run() {
        if(System.getProperty("os.name").contains("Windows")){
            
            getPath(new File("./"),".jxta");
            
        }else{
            if(System.getProperty("os.name").contains("Linux")){
                System.out.println("hello linux!!!");
                getPath(new File("/home/"),"Matrix.txt");
            }
        }
        if(al.isEmpty()){
            
            frame_set = new Frame_set(this);
        frame_set.setVisible(true);
            
        }else{
            
            Choix1 choix1 = new Choix1(this,al);
            choix1.setVisible(true);
        }
        

    }

    @Override
    public void rendezvousEvent(RendezvousEvent re) {
        if (re.getType() == re.RDVCONNECT) {
            notify();
        }
    }
    
   
     
     public void getPath(File f,String s){
     if(f.getName().equals(s)){
          al.add(f.getPath());
     }
        
 
		File[] liste_fils = f.listFiles();
 
		if(liste_fils!=null)
		{
			for(int i=0;i<liste_fils.length;i++)
			{
				getPath(liste_fils[i],s);
			}
                       
                     
        
    
                }
     }

    private PeerGroupID createPeerGroupID(String ID) {
       PeerGroupID tempPeerGroupID = null;
        try {
            tempPeerGroupID = (PeerGroupID) IDFactory.fromURL(new URL("urn", "",ID));
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
                System.out.println("Valid PeerGroupID has been created");
                return tempPeerGroupID;
    }

    private PeerGroup createPeerGroup(PeerGroupID myPeerGroupID, String myPeerGroupName, String myPeerGroupDescription) throws Exception {
        PeerGroupAdvertisement Aniss_HakimGroupAdvertisement;
        PeerGroup tempPeerGroup = null;
        ModuleImplAdvertisement myGroupImpl ,myNewImplAdv = null;
            
                myGroupImpl = netPeerGroup.getAllPurposePeerGroupImplAdvertisement();
                
                /*StructuredTextDocument paramDoc = (StructuredTextDocument) myGroupImpl.getDocument(XMLMIMETYPE);
                
                DOMBuilder builder = new DOMBuilder();
                
                Document doc = builder.build(paramDoc.getStream());
                Element membershipElement = getElementMSID(getParamElement(doc.getRootElement()),"urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000050106");
                updateElementMSID(membershipElement,myGroupImpl);
                XMLOutputter outputter = new XMLOutputter();
                myNewImplAdv = (ModuleImplAdvertisement) AdvertisementFactory.newAdvertisement(XMLMIMETYPE,new ByteArrayInputStream(outputter.outputString(doc).getBytes()));
                if(!myNewImplAdv.getModuleSpecID().equals(peerGroup.allPurposePeerGroupSpecID)){
                    myNewImplAdv.setModuleSpecID(IDFactory.newModuleSpecID(myNewImplAdv.getModuleSpecID().getBaseClass()));
                }else{
                    myNewImplAdv.setModuleSpecID(((ModuleSpecID) IDFactory.fromURL(new URL("urn","","jxta:uuid-"+"DeadBeefDeafBabaFeedBabe00000001"+"05"+"06"))));
                    
                    
                }
                myDiscoveryService.publish(myNewImplAdv,DiscoveryService.ADV,PeerGroup.DEFAULT_LIFETIME);
                myDiscoveryService.remotePublish(myNewImplAdv,DiscoveryService.ADV);
                
                
                
                */
                
                
                
           
            
            
            Aniss_HakimGroupAdvertisement = (PeerGroupAdvertisement) AdvertisementFactory.newAdvertisement(PeerGroupAdvertisement.getAdvertisementType());
            Aniss_HakimGroupAdvertisement.setPeerGroupID(myPeerGroupID);
            Aniss_HakimGroupAdvertisement.setModuleSpecID(myGroupImpl.getModuleSpecID());
            Aniss_HakimGroupAdvertisement.setName(myPeerGroupName);
            Aniss_HakimGroupAdvertisement.setDescription(myPeerGroupDescription);
            
            StructuredTextDocument loginInfo = (StructuredTextDocument) StructuredDocumentFactory.newStructuredDocument(new MimeMediaType("text/xml"),"Parm");
            String loginString = Name + ":" + UpdatedPasswdMembershipService.makePsswd(Password)+":";
            TextElement loginElement = loginInfo.createElement("login",loginString);
            loginInfo.appendChild(loginElement);
            Aniss_HakimGroupAdvertisement.putServiceParam(PeerGroup.membershipClassID, loginInfo);
            
            System.out.println("New PeerGroupAdvertisement has been created");
            
            try{
                
                myDiscoveryService.publish(Aniss_HakimGroupAdvertisement,DiscoveryService.GROUP,PeerGroup.DEFAULT_LIFETIME);
                myDiscoveryService.remotePublish(Aniss_HakimGroupAdvertisement,DiscoveryService.GROUP);
                
                
            }catch(Exception e){
                e.printStackTrace();
                System.exit(-1);
            }
            
            System.out.println("New PeerGroupAdvertisement has been published");
            System.out.println(Aniss_HakimGroupAdvertisement);
            
            try{
                
                tempPeerGroup = netPeerGroup.newGroup(Aniss_HakimGroupAdvertisement);
            }catch(Exception e){
                e.printStackTrace();
                System.exit(-1);
            }
            
            System.out.println("New PeerGroup has been created");
            
            return tempPeerGroup;
            
    }
    
    Element getParamElement(Element theRootElement){
        List elements = theRootElement.getChildren();
        Iterator itr = elements.iterator();
        Element currentElement = null;
        
        while(itr.hasNext()){
            currentElement = (Element) itr.next();
            if(currentElement.getName().equals("Parm")) return currentElement;
            
        }
        
        return null;
    }
    
    Element getElementMSID(Element theParamElement, String theMatchingID){
        List elemnts = theParamElement.getChildren(),innerElements = null, tempList = null;
        Iterator paramItr = elemnts.iterator();
        Element returnElement = null, localElement = null, tempElement=null;
        
        while(paramItr.hasNext()){
            returnElement = (Element) paramItr.next();
            if(returnElement.getName().equals("Svc")){
                tempList = returnElement.getChildren();
                tempElement = (Element) tempList.get(0);
                innerElements = tempElement.getChildren();
                Iterator svcItr = innerElements.iterator();
                while(svcItr.hasNext()){
                    localElement = (Element) svcItr.next();
                    
                    if(localElement.getName().equals("MSID") && localElement.getTextTrim().equals(theMatchingID)) return returnElement;
                }
            }
            
        }
        
        return null;
        
        
    }
    
    private void findAdvertisement(String searchKey, String searchValue) throws PeerGroupException, ProtocolNotSupportedException{
        Enumeration myLocalEnum = null;
        PeerGroupAdvertisement localAniss_HakimGroupAdv = null;
        
        System.out.println("Trying to find advertisement");
        
        try{
            myLocalEnum = myDiscoveryService.getLocalAdvertisements(DiscoveryService.GROUP, searchKey, searchValue);
            
            if((myLocalEnum!=null) && (myLocalEnum.hasMoreElements())){
                System.out.println("Found local advertisement");
                PeerGroupAdvertisement myFoundPGA = null;
                while(myLocalEnum.hasMoreElements()){
                    myFoundPGA = (PeerGroupAdvertisement) myLocalEnum.nextElement();
                    if(myFoundPGA.getName().equals(searchValue)){
                        localAniss_HakimGroupAdv = myFoundPGA;
                        break;
                    }
                }
                
                if(localAniss_HakimGroupAdv!=null){
                    
                    System.out.println("Creating new group");
                    
                    Aniss_Hakim_PeerGroup = netPeerGroup.newGroup(localAniss_HakimGroupAdv);
                    joinGroup(Aniss_Hakim_PeerGroup);
                }
            }else{
                DiscoveryListener myDiscoveryListener = new DiscoveryListener() {

                    @Override
                    public void discoveryEvent(DiscoveryEvent de) {
                        Enumeration enum1;
                        String str;
                        
                        System.out.println("Found remote advertisement");
                        
                        DiscoveryResponseMsg myMessage = de.getResponse();
                        
                        enum1 = myMessage.getResponses();
                        
                        str = (String) enum1.nextElement();
                        
                        try{
                            PeerGroupAdvertisement myPeerGroupAdvertisement = (PeerGroupAdvertisement) AdvertisementFactory.newAdvertisement(XMLMIMETYPE,new ByteArrayInputStream(str.getBytes()));
                            
                            System.out.println("Creating new group");
                            
                            Aniss_Hakim_PeerGroup = netPeerGroup.newGroup(myPeerGroupAdvertisement);
                            joinGroup(Aniss_Hakim_PeerGroup);
                        }catch(Exception e){
                            e.printStackTrace();
                            System.exit(-1);
                        }
                    }
                };
                
                System.out.println("Launching remote discovery service");
                
                myDiscoveryService.getRemoteAdvertisements(null, DiscoveryService.GROUP, searchKey, searchValue, 1,myDiscoveryListener);
                
            }
        }catch(Exception e){
            System.out.println("Error during advertisement search");
            System.exit(-1);
        }
    }
    
   
   
    
    

    private void joinGroup(PeerGroup myLocalGroup) throws PeerGroupException, ProtocolNotSupportedException {
        StructuredDocument myCredentials = null;
        try {
        AuthenticationCredential myAuthenticationCredential =
        new AuthenticationCredential(myLocalGroup, null, myCredentials);
        MembershipService myMembershipService = myLocalGroup.getMembershipService();
        net.jxta.membership.Authenticator myAuthenticator = myMembershipService.apply(myAuthenticationCredential);
        
        AuthenticateMe(myAuthenticator, Name, Password);
        if(!myAuthenticator.isReadyForJoin()){
            System.out.println("Authenticator is not complete");
            return;
        }

            
        myMembershipService.join(myAuthenticator);
       
        System.out.println("Group has been joined\n");
        } catch (Exception e) {
        System.out.println("Authentication failed - group not joined\n");
        e.printStackTrace();
        System.exit(-1);
        }
        }
    
    void AuthenticateMe(Authenticator theAuthenticator, String login, String password){
        Method [] ourMethods = theAuthenticator.getClass().getMethods();
        try{
            
            for(int meth = 0; meth < ourMethods.length; meth++){
                if(ourMethods[meth].getName().equals("setAuth1Identity")){
                    Object[] authLogin = {login};
                    Method aMethod = (Method) ourMethods[meth];
                    aMethod.invoke(theAuthenticator, authLogin);
                }else if(ourMethods[meth].getName().equals("setAuth2_Password")){
                    Object[] authPassword ={password};
                    Method aMethod = (Method) ourMethods[meth];
                    aMethod.invoke(theAuthenticator, authPassword);
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    
}
