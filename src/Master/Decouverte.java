package Master;



import Launcher.Frame_1;
import java.awt.Dimension;
import java.util.Enumeration;
import java.util.LinkedList;
import javax.naming.ldap.ManageReferralControl;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.xml.ws.Endpoint;
import net.jxta.discovery.DiscoveryEvent;
import net.jxta.discovery.DiscoveryListener;
import net.jxta.discovery.DiscoveryService;
import net.jxta.endpoint.EndpointService;
import net.jxta.peer.PeerID;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupFactory;
import net.jxta.pipe.OutputPipe;
import net.jxta.platform.NetworkManager;
import net.jxta.protocol.DiscoveryResponseMsg;
import net.jxta.protocol.PeerAdvertisement;
import net.jxta.protocol.PeerGroupAdvertisement;
import net.jxta.protocol.RouteAdvertisement;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aniss
 */
public class Decouverte extends Thread implements DiscoveryListener{
    public  MasterPeer masterPeer;
    public DiscoveryService discovery=null;
    public PeerGroup nPeerGroup;
    public static LinkedList l = new LinkedList();
    

    public Matrix a,b;
    public LinkedList list=new LinkedList();
    public String chaine;
  /*  public Decouverte(LinkedList liste){
        this.l=liste;
    }*/
    
    private EndpointService myEndpointService;
    public NetworkManager TheNetworkManager;
    public Frame_1 frame_1;
    public JTextArea myJTextArea;
    public JProgressBar myJProgressBar;

    public Decouverte(PeerGroup nPeerGroup1 ,String chaine,Matrix a,Matrix b, NetworkManager myNetworkManager, Frame_1 frame_1) {
        this.TheNetworkManager = myNetworkManager;
        this.frame_1=frame_1;
        this.chaine=chaine;
        this.a=a;
        this.b=b;
        
        
       
       
        this.nPeerGroup=nPeerGroup1;
        
    }

    public Decouverte(PeerGroup nPeerGroup, Matrix a, Matrix b, LinkedList list, String chaine,NetworkManager myNetworkManager) {
        this.TheNetworkManager = myNetworkManager;
        this.a=a;
        this.b=b;
        this.list=list;
        this.chaine=chaine;
        this.nPeerGroup=nPeerGroup;
        
        
        
       
    }
    
    
    
      
    
  
    
    
    
    
    
    
    
    
    
    
    public void run(){
        
        
        
        
            
        
       
        
       try{
           
           this.myJProgressBar = frame_1.GetJProgressBar();
           Dimension o = this.myJProgressBar.getSize();
           int x = o.width;
           this.myJTextArea = frame_1.GetJTextArea();
           this.discovery=nPeerGroup.getDiscoveryService();
           
           this.discovery.addDiscoveryListener(this);
        int j=x/7;
        int i=0;
        while(i<4 && j<x){
            
            this.myJProgressBar.setValue(j);
            
            
            this.myJTextArea.append("Sending a discovery message \n");
            //discovery.getLocalAdvertisements(DiscoveryService.PEER, null, null);
           
            this.myJTextArea.append("------------------------------------------------\n");
           
            this.discovery.getRemoteAdvertisements(null, DiscoveryService.PEER, null, null, 10);
           
            
            
            try{
                Thread.sleep(3*1000);
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
            i++;
            j=j+(x/7);
        }
        list=this.Resultat_recherche();
        System.out.println("Découverte leste est : "+list);
       MasterPeer mp = new MasterPeer(list, nPeerGroup);
       mp.SetLinkedlist(list);
        
       }catch(Exception e){
           e.printStackTrace();
       }
       
        
        
        
                
               
                
               
            
   
        
        
    }
    
    
    
    

    @Override
    public synchronized void discoveryEvent(DiscoveryEvent de) {
         JTextArea myJTextArea = frame_1.GetJTextArea();
        
        System.out.println("''''''''''''''''''''''''''''");
       
        String name="Unknown";
        
        DiscoveryResponseMsg res=de.getResponse();
        
        PeerAdvertisement peerAdv=res.getPeerAdvertisement();
        
       if(peerAdv!=null){
           name=peerAdv.getName();
       }
       
        myJTextArea.append("Got a discovery response [" +res.getResponseCount()+ " element] from peer : "+name+"\n");
       
       
       
        myJTextArea.append("------------------------------------------------\n");
        PeerAdvertisement adv=null;
        
        Enumeration enum1=res.getAdvertisements();
    
        
       
        if(enum1!=null){
            while(enum1.hasMoreElements()){
                
                adv= (PeerAdvertisement) enum1.nextElement();
            
                myJTextArea.append("Peer name : "+adv.getName()+"\n");
                myJTextArea.append("------------------------------------------------\n");
               
                LinkedList l=Retourner(adv.getPeerID());
                
            }
            
       
        }
        
       
        
       
        
        
        
    }

    public LinkedList Retourner(PeerID peerID) {
        if(!l.contains(peerID)){
            l.add(peerID);
            
        }
        
        
        
        return l;
        
        
        
        
    }
    
    public LinkedList Resultat_recherche(){
        return  l;
    }
    
    
    
    
       
       
        
        
     
    }
    
    
    
    
    
   
    
    
    
  

    
    
    
    
    
    
    

