package Master;



import Worker.WorkerPeer;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.jxta.peer.PeerID;
import net.jxta.peergroup.PeerGroup;
import net.jxta.pipe.InputPipe;
import net.jxta.pipe.OutputPipe;
import net.jxta.pipe.OutputPipeEvent;
import net.jxta.pipe.PipeService;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdelhakim
 */
public class Ordonnaceur {
    
    private Matrix a,b;
    private String chaine;
    private LinkedList copie = new LinkedList(),liste=new LinkedList();
    private PeerGroup netPeerGroup;
    private MasterPeer masterPeer;
    public Scanner sc = new Scanner(System.in);
    public OutputPipe myOutputPipe;
    public InputPipe myInputPipe;
    private WorkerPeer workerPeer;
    
    

    Ordonnaceur(Matrix a, Matrix b, String chaine, LinkedList list,MasterPeer aThis, PeerGroup netPeerGroupx,InputPipe myInputPipe1) {
        
        this.a=a;
        this.b=b;
        this.chaine=chaine;
        this.myInputPipe = myInputPipe1;
        this.masterPeer=aThis;
        this.netPeerGroup=netPeerGroupx;
        
        
         
     
        
        
        
         int g = 0;
                                    while(g<list.size()){
                                      
                                            
                                             copie.add(list.get(g));
                   
                                            g++;
                                            
                                        
                                        
                   
                   
                                    }
                                    
                                    
                                    int r = 0;
                                    while(r<copie.size()){
                                       
                                            
                                            
                                            liste.add(copie.get(r));
                   
                                            r++;
                                        
                                        
                                         
                   
                   
                                    }
        
        
        
                
        
        
        
    }
    
    
     public Ordonnaceur(Matrix a, Matrix b, String chaine, LinkedList list, WorkerPeer aThis, PeerGroup netPeerGroup) {
        this.a=a;
        this.b=b;
        this.chaine=chaine;
        
        this.workerPeer = aThis;
        this.netPeerGroup = netPeerGroup;
        
        
        
        int g = 0;
                                    while(g<list.size()){
                                     
                                         copie.add(list.get(g));
                   
                                            g++;
                                        
                   
                   
                                    }
                                    
                                    
                                    int r = 0;
                                    while(r<copie.size()){
                                        
                                        
                                         liste.add(copie.get(r));
                   
                                            r++;
                   
                   
                                    }
        
        
    }
    
    

    public void OrdonnancerAddition_Soustraction_Division() throws IOException {
        
        
       
            
           if(masterPeer!=null){
               
                 
          if(a.getNbLines()==copie.size() || a.getNbLines() < copie.size()){
                
               
                int i=0;
                while(i<a.getNbLines()){
                    
                    
                        
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                        
                            masterPeer.run2(i,myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                            myOutputPipe.close();
                             i++;
                             
                         
                       
                }       
                         
                    
                   
                }else{
                
                
              
              LinkedList roue_de_secours = new LinkedList();
              
                    if(a.getNbLines()>copie.size()){
                       
                         int m=0;
                        while(!copie.isEmpty() && m<a.getNbLines()){
                        
                         
                           
                    
                                
                                
                                
                                System.out.println("Et voici votre copie encore une fois :"+copie);
                        
                                 PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                        
                                masterPeer.run2(m,myOutputPipe);
                            
                                
                                roue_de_secours.add(peerID);
                                copie.removeFirst();
                                
                                m++;
                                
                                
                        }
                             
                         
                       
                            
                        
                            
                            
                            
                            
                            
                            
                            int h = roue_de_secours.size();
                            
                            while(h<a.getNbLines()){
                                
                    
                                
                                if(!roue_de_secours.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) roue_de_secours.getFirst();
                         
                            
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                
                                    masterPeer.run2(h,myOutputPipe);
                            
                                
                            
                                    roue_de_secours.removeFirst();
                                   
                                    h++;
                                    
                                    
                                }else{
                                    
                                   
                                    int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         roue_de_secours.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) roue_de_secours.getFirst();
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    masterPeer.run2(h, myOutputPipe);
                                    
                                    roue_de_secours.removeFirst();
                                   
                                    
                                    h++;
                                    
                                    
                                }
                        
                                
                                
                                
                             
                         
                       
                            }
                        
                        
                        
                        
                    }
              
                 }
               
           }else{
               if(workerPeer!=null){
                     
          if(a.getNbLines()==copie.size() || a.getNbLines() < copie.size()){
                
               
                int i=0;
                while(i<a.getNbLines()){
                    
                    
                        
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                        
                            workerPeer.run2(i,myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                            myOutputPipe.close();
                             i++;
                             
                         
                       
                }       
                         
                    
                   
                }else{
                
                
              
              LinkedList roue_de_secours = new LinkedList();
              
                    if(a.getNbLines()>copie.size()){
                       
                         int m=0;
                        while(!copie.isEmpty() && m<a.getNbLines()){
                        
                         
                           
                    
                                
                                
                                
                                System.out.println("Et voici votre copie encore une fois :"+copie);
                        
                                 PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                        
                                workerPeer.run2(m,myOutputPipe);
                            
                                
                                roue_de_secours.add(peerID);
                                copie.removeFirst();
                                
                                m++;
                                
                                
                        }
                             
                         
                       
                            
                        
                            
                            
                            
                            
                            
                            
                            int h = roue_de_secours.size();
                            
                            while(h<a.getNbLines()){
                                
                    
                                
                                if(!roue_de_secours.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) roue_de_secours.getFirst();
                         
                            
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                
                                    workerPeer.run2(h,myOutputPipe);
                            
                                
                            
                                    roue_de_secours.removeFirst();
                                   
                                    h++;
                                    
                                    
                                }else{
                                    
                                   
                                    int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         roue_de_secours.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) roue_de_secours.getFirst();
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    workerPeer.run2(h, myOutputPipe);
                                    
                                    roue_de_secours.removeFirst();
                                   
                                    
                                    h++;
                                    
                                    
                                }
                        
                                
                                
                                
                             
                         
                       
                            }
                        
                        
                        
                        
                    }
              
                 }
               }
           }
            
            
         
            
        
        
    }

    public void OrdonnancerMultiplication() throws IOException {
        
        
        if(masterPeer!=null){
            
             
                if((a.getNbLines()*b.getNbColumns())==copie.size() || (a.getNbLines()*b.getNbColumns())<copie.size()){
                    
                    
                      int i=0;
                      
                      while(i < a.getNbLines()){
                         int j = 0; 
                           while(j<b.getNbColumns()){
                    
                    
                        
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run3(i,j,myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                             j++;
                             
                         
                       
                } 
                           i++;
                          
                      }
                     
                        
                    
                }else{
                    
              LinkedList roue_de_secours = new LinkedList();
              
                    if((a.getNbLines()*b.getNbColumns())>copie.size()){
                       
                        
                             
                         
                       
                            
                        
                            
                            
                            
                            
                            
                            
                            
                            
                           int h = 0;
                            
                            
                            
                            
                            while(h<a.getNbLines()){
                                
                                
                                
                                int w = 0;
                                
                                while(w<b.getNbColumns()){
                                    
                                    
                                    
                                    
                                    
                                    
                                   
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    masterPeer.run3(h,w,myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    w++;
                                    
                                    
                                }else{
                                    
                                   
                                    int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    masterPeer.run3(h,w, myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    w++;
                                    
                                    
                                }
                                    
                                }
                                
                    
                                
                        
                                
                                
                               h++; 
                             
                         
                       
                            }
                        
                        
                        
                        
                    }
                }
                
                
            
        }else{
            if(workerPeer!=null){
                System.out.println("voici votre copie du peer 3 : "+copie);
                
                 
                if((a.getNbLines()*b.getNbColumns())==copie.size() || (a.getNbLines()*b.getNbColumns())<copie.size()){
                    
                    
                      int i=0;
                      
                      while(i < a.getNbLines()){
                         int j = 0; 
                           while(j<b.getNbColumns()){
                    
                    
                        
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run3(i,j,myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                             j++;
                             
                         
                       
                } 
                           i++;
                          
                      }
                     
                        
                    
                }else{
                    
              LinkedList roue_de_secours = new LinkedList();
              
                    if((a.getNbLines()*b.getNbColumns())>copie.size()){
                       
                        
                             
                         
                       
                            
                        
                            
                            
                            
                            
                            
                            
                            
                            
                           int h = 0;
                            
                            
                            
                            
                            while(h<a.getNbLines()){
                                
                                
                                
                                int w = 0;
                                
                                while(w<b.getNbColumns()){
                                    
                                    
                                    
                                    
                                    
                                    
                                   
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    workerPeer.run3(h,w,myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    w++;
                                    
                                    
                                }else{
                                    
                                   
                                    int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    workerPeer.run3(h,w, myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    w++;
                                    
                                    
                                }
                                    
                                }
                                
                    
                                
                        
                                
                                
                               h++; 
                             
                         
                       
                            }
                        
                        
                        
                        
                    }
                }
                
                
            }
        }
         
                
               
            
    }

    public void OrdonnancerTranspose() throws IOException {
        
                    
                   if(masterPeer!=null){
                       
                       
                        
                    if((a.getNbLines()==copie.size() && b.getNbLines()==copie.size()) || (a.getNbLines()<copie.size() && b.getNbLines()<copie.size())){
                        int i =0;
                        while(i<a.getNbLines()){
                            
                                
                                
                                
                               
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run4(i,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            i++;
                        }
                        
                        
                        
                         int g = 0;
                         while(g<liste.size()){
                              PeerID peerID=(PeerID) liste.get(g);
                                        
                               copie.add(liste.get(g));
                   
                               g++;
                   
                   
                               }
                         
                          int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                            
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run4(j,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                          
                                    
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    }else{
                        
                        if(a.getNbLines()>copie.size() && b.getNbLines()>copie.size()){
                            
                            
                             int h = 0;
                            
                            
                            
                            
                            while(h<a.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                  
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    masterPeer.run4(h,"a",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    h++;
                                    
                                    
                                }else{
                                    
                                   
                                    int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    masterPeer.run4(h,"a", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    h++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                
                                
                                
                             
                         
                       
                            }
                            
                            
                            
                            
                             int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                    
                               int w = 0;
                            
                            
                            
                            
                            while(w<b.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    masterPeer.run4(w,"b",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    w++;
                                    
                                    
                                }else{
                                    
                                   
                                    int p = 0;
                                    while(p<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(p);
                                        
                                         copie.add(liste.get(p));
                   
                                            p++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    masterPeer.run4(w,"b", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    w++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                
                                
                                
                             
                         
                       
                            }      
                        
                            
                            
                            
                            
                            
                            
                            
                        }else{
                            
                            
                            
                            if(a.getNbLines()>copie.size() && (b.getNbLines()<copie.size() || b.getNbLines()==copie.size())){
                                
                                
                                  int h = 0;
                            
                            
                            
                            
                            while(h<a.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                 
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    masterPeer.run4(h,"a",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    h++;
                                    
                                    
                                }else{
                                    
                                   
                                    int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    masterPeer.run4(h,"a", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    h++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                
                                
                                
                             
                         
                       
                            }
                            
                            
                            
                            
                             int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                    
                                    
                                    
                                      int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                              
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run4(j,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                                    
                                
                                
                                
                                
                                
                                
                            }else{
                                
                                
                                if((a.getNbLines()==copie.size() || a.getNbLines()<copie.size()) && b.getNbLines()>copie.size()){
                                    
                                    
                                       int w = 0;
                            
                            
                            
                            
                            while(w<b.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                   
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    masterPeer.run4(w,"b",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    w++;
                                    
                                    
                                }else{
                                    
                                   
                                    int p = 0;
                                    while(p<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(p);
                                        
                                         copie.add(liste.get(p));
                   
                                            p++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    masterPeer.run4(w,"b", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    w++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                    
                                }
                            
                            
                            
                            
                             int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                    
                                    
                                       int i =0;
                        while(i<a.getNbLines()){
                            
                                
                                
                                
                                
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run4(i,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            i++;
                        }
                            }else{
                                    if((a.getNbLines()==copie.size() || a.getNbLines()<copie.size()) && b.getNbLines()<copie.size()){
                                        
                                        
                                        
                                        
                                         int i =0;
                        while(i<a.getNbLines()){
                            
                                
                                
                                
                          
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run4(i,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            i++;
                        }
                        
                        
                        
                        
                         int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                            int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                               
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run4(j,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                                        
                                    }else{
                                        if(a.getNbLines()<copie.size() && (b.getNbLines()==copie.size() || b.getNbLines()<copie.size())){
                                            
                                            
                                            
                                                              int i =0;
                        while(i<a.getNbLines()){
                            
                                
                                
                                
                               
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run4(i,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            i++;
                        }
                        
                        
                        
                        
                         int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                            int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                               
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run4(j,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                                            
                                            
                                            
                                            
                                            
                                        }
                                    }
                                }
                            
                            
                            
                            
                            
                        }
                        
                        
                        
                        
                    }
                    
                    
                    
                    
                }
            
                       
                   
                    
                   
    }else{
                       if(workerPeer!=null){
                           
                                if((a.getNbLines()==copie.size() && b.getNbLines()==copie.size()) || (a.getNbLines()<copie.size() && b.getNbLines()<copie.size())){
                        int i =0;
                        while(i<a.getNbLines()){
                            
                                
                                
                                
                               
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run4(i,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            i++;
                        }
                        
                        
                        
                         int g = 0;
                         while(g<liste.size()){
                              PeerID peerID=(PeerID) liste.get(g);
                                        
                               copie.add(liste.get(g));
                   
                               g++;
                   
                   
                               }
                         
                          int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                            
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run4(j,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                          
                                    
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    }else{
                        
                        if(a.getNbLines()>copie.size() && b.getNbLines()>copie.size()){
                            
                            
                             int h = 0;
                            
                            
                            
                            
                            while(h<a.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                  
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    workerPeer.run4(h,"a",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    h++;
                                    
                                    
                                }else{
                                    
                                   
                                    int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    workerPeer.run4(h,"a", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    h++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                
                                
                                
                             
                         
                       
                            }
                            
                            
                            
                            
                             int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                    
                               int w = 0;
                            
                            
                            
                            
                            while(w<b.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    workerPeer.run4(w,"b",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    w++;
                                    
                                    
                                }else{
                                    
                                   
                                    int p = 0;
                                    while(p<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(p);
                                        
                                         copie.add(liste.get(p));
                   
                                            p++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    workerPeer.run4(w,"b", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    w++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                
                                
                                
                             
                         
                       
                            }      
                        
                            
                            
                            
                            
                            
                            
                            
                        }else{
                            
                            
                            
                            if(a.getNbLines()>copie.size() && (b.getNbLines()<copie.size() || b.getNbLines()==copie.size())){
                                
                                
                                  int h = 0;
                            
                            
                            
                            
                            while(h<a.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                 
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    workerPeer.run4(h,"a",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    h++;
                                    
                                    
                                }else{
                                    
                                   
                                    int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    workerPeer.run4(h,"a", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    h++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                
                                
                                
                             
                         
                       
                            }
                            
                            
                            
                            
                             int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                    
                                    
                                    
                                      int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                              
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run4(j,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                                    
                                
                                
                                
                                
                                
                                
                            }else{
                                
                                
                                if((a.getNbLines()==copie.size() || a.getNbLines()<copie.size()) && b.getNbLines()>copie.size()){
                                    
                                    
                                       int w = 0;
                            
                            
                            
                            
                            while(w<b.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                   
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    workerPeer.run4(w,"b",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    w++;
                                    
                                    
                                }else{
                                    
                                   
                                    int p = 0;
                                    while(p<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(p);
                                        
                                         copie.add(liste.get(p));
                   
                                            p++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    workerPeer.run4(w,"b", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    w++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                    
                                }
                            
                            
                            
                            
                             int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                    
                                    
                                       int i =0;
                        while(i<a.getNbLines()){
                            
                                
                                
                                
                                
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run4(i,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            i++;
                        }
                            }else{
                                    if((a.getNbLines()==copie.size() || a.getNbLines()<copie.size()) && b.getNbLines()<copie.size()){
                                        
                                        
                                        
                                        
                                         int i =0;
                        while(i<a.getNbLines()){
                            
                                
                                
                                
                          
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run4(i,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            i++;
                        }
                        
                        
                        
                        
                         int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                            int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                               
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run4(j,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                                        
                                    }else{
                                        if(a.getNbLines()<copie.size() && (b.getNbLines()==copie.size() || b.getNbLines()<copie.size())){
                                            
                                            
                                            
                                                              int i =0;
                        while(i<a.getNbLines()){
                            
                                
                                
                                
                               
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run4(i,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            i++;
                        }
                        
                        
                        
                        
                         int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                            int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                               
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run4(j,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                                            
                                            
                                            
                                            
                                            
                                        }
                                    }
                                }
                            
                            
                            
                            
                            
                        }
                        
                        
                        
                        
                    }
                    
                    
                    
                    
                }
            
                       
                 
                           
                       }
                   }
                   
    }

    public void OrdonnancerMultiplication_par_nombre() throws IOException {
        
                        
                        
                        if(masterPeer!=null){
                             int v = Integer.parseInt(JOptionPane.showInputDialog(null,"Veuillez entrer un nombre svp!","Définition de votre nombre",JOptionPane.QUESTION_MESSAGE));
                        
                    System.out.println("Votre nombre est "+v);    
                      
                        if((a.getNbLines()==copie.size() && b.getNbLines()==copie.size()) || (a.getNbLines()<copie.size() && b.getNbLines()<copie.size())){
                            
                            int i=0;
                while(i<a.getNbLines()){
                    
                    
                        
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                        
                            masterPeer.run5(i,v,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                             i++;
                             
                         
                       
                }
                
                
                
                
                   int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                    
                                       int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                               
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run5(j,v,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                            
                            
                            
                        }else{
                            
                            
                            
                             if(a.getNbLines()>copie.size() && b.getNbLines()>copie.size()){
                            
                            
                             int h = 0;
                            
                            
                            
                            
                            while(h<a.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                  
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    masterPeer.run5(h,v,"a",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    h++;
                                    
                                    
                                }else{
                                    
                                   
                                    int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    masterPeer.run5(h,v,"a", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    h++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                
                                
                                
                             
                         
                       
                            }
                            
                            
                            
                            
                             int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                    
                               int w = 0;
                            
                            
                            
                            
                            while(w<b.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    masterPeer.run5(w,v,"b",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    w++;
                                    
                                    
                                }else{
                                    
                                   
                                    int p = 0;
                                    while(p<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(p);
                                        
                                         copie.add(liste.get(p));
                   
                                            p++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    masterPeer.run5(w,v,"b", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    w++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                
                                
                                
                             
                         
                       
                            }      
                        
                            
                            
                            
                            
                           
                        }else{
                                  if(a.getNbLines()>copie.size() && (b.getNbLines()<copie.size() || b.getNbLines()==copie.size())){
                                
                                
                                  int h = 0;
                            
                            
                            
                            
                            while(h<a.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                 
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    masterPeer.run5(h,v,"a",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    h++;
                                    
                                    
                                }else{
                                    
                                   
                                    int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    masterPeer.run5(h,v,"a", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    h++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                
                                
                                
                             
                         
                       
                            }
                            
                            
                            
                            
                             int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                    
                                    
                                    
                                      int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                              
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run5(j,v,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                                    
                                
                                
                                
                                  }else{
                                       if((a.getNbLines()==copie.size() || a.getNbLines()<copie.size()) && b.getNbLines()>copie.size()){
                                    
                                    
                                       int w = 0;
                            
                            
                            
                            
                            while(w<b.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                   
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    masterPeer.run5(w,v,"b",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    w++;
                                    
                                    
                                }else{
                                    
                                   
                                    int p = 0;
                                    while(p<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(p);
                                        
                                         copie.add(liste.get(p));
                   
                                            p++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                                    masterPeer.run5(w,v,"b", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    w++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                    
                                }
                            
                            
                            
                            
                             int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                    
                                    
                                       int i =0;
                        while(i<a.getNbLines()){
                            
                                
                                
                                
                                
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run5(i,v,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            i++;
                        }
                            }else{
                                            if((a.getNbLines()==copie.size() || a.getNbLines()<copie.size()) && b.getNbLines()<copie.size()){
                                        
                                        
                                        
                                        
                                         int i =0;
                        while(i<a.getNbLines()){
                            
                                
                                
                                
                          
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run5(i,v,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            i++;
                        }
                        
                        
                        
                        
                         int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                            int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                               
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run5(j,v,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                                        
                                    }else{
                                                
                                                
                                                
                                                         if(a.getNbLines()<copie.size() && (b.getNbLines()==copie.size() || b.getNbLines()<copie.size())){
                                            
                                            
                                            
                                                              int i =0;
                        while(i<a.getNbLines()){
                            
                                
                                
                                
                               
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run5(i,v,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            i++;
                        }
                        
                        
                        
                        
                         int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                            int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                               
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = masterPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            masterPeer.run5(j,v,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                                            
                                            
                                            
                                            
                                            
                                        }
                                                
                                            }
                                       }
                                      
                                  }
                             }
                        
                        
                        
                        
                            
                        }
                       
        
                            
                            
                                            
          
                    
    }else{
                            if(workerPeer!=null){
                                            int v = Integer.parseInt(JOptionPane.showInputDialog(null,"Veuillez entrer un nombre svp!","Définition de votre nombre",JOptionPane.QUESTION_MESSAGE));
                        
                    System.out.println("Votre nombre est "+v);    
                      
                        if((a.getNbLines()==copie.size() && b.getNbLines()==copie.size()) || (a.getNbLines()<copie.size() && b.getNbLines()<copie.size())){
                            
                            int i=0;
                while(i<a.getNbLines()){
                    
                    
                        
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                        
                            workerPeer.run5(i,v,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                             i++;
                             
                         
                       
                }
                
                
                
                
                   int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                    
                                       int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                               
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run5(j,v,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                            
                            
                            
                        }else{
                            
                            
                            
                             if(a.getNbLines()>copie.size() && b.getNbLines()>copie.size()){
                            
                            
                             int h = 0;
                            
                            
                            
                            
                            while(h<a.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                  
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    workerPeer.run5(h,v,"a",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    h++;
                                    
                                    
                                }else{
                                    
                                   
                                    int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    workerPeer.run5(h,v,"a", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    h++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                
                                
                                
                             
                         
                       
                            }
                            
                            
                            
                            
                             int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                    
                               int w = 0;
                            
                            
                            
                            
                            while(w<b.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    workerPeer.run5(w,v,"b",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    w++;
                                    
                                    
                                }else{
                                    
                                   
                                    int p = 0;
                                    while(p<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(p);
                                        
                                         copie.add(liste.get(p));
                   
                                            p++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    workerPeer.run5(w,v,"b", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    w++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                
                                
                                
                             
                         
                       
                            }      
                        
                            
                            
                            
                            
                           
                        }else{
                                  if(a.getNbLines()>copie.size() && (b.getNbLines()<copie.size() || b.getNbLines()==copie.size())){
                                
                                
                                  int h = 0;
                            
                            
                            
                            
                            while(h<a.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                 
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    workerPeer.run5(h,v,"a",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    h++;
                                    
                                    
                                }else{
                                    
                                   
                                    int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    workerPeer.run5(h,v,"a", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    h++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                
                                
                                
                             
                         
                       
                            }
                            
                            
                            
                            
                             int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                    
                                    
                                    
                                      int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                              
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run5(j,v,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                                    
                                
                                
                                
                                  }else{
                                       if((a.getNbLines()==copie.size() || a.getNbLines()<copie.size()) && b.getNbLines()>copie.size()){
                                    
                                    
                                       int w = 0;
                            
                            
                            
                            
                            while(w<b.getNbLines()){
                                
                                
                                
                                
                                    
                                    
                                    
                                    
                                    
                                    
                                   
                                if(!copie.isEmpty()){
                                    
                                    
                                    
                                     PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    
                                    
                                
                                    workerPeer.run5(w,v,"b",myOutputPipe);
                            
                                
                            
                                   copie.removeFirst();
                                    
                                    w++;
                                    
                                    
                                }else{
                                    
                                   
                                    int p = 0;
                                    while(p<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(p);
                                        
                                         copie.add(liste.get(p));
                   
                                            p++;
                   
                   
                                    }
                                    PeerID peerID=(PeerID) copie.getFirst();
                                    myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                                    workerPeer.run5(w,v,"b", myOutputPipe);
                                    
                                    copie.removeFirst();
                                   
                                    w++;
                                    
                                    
                                }
                                    
                                
                                
                    
                                
                        
                                    
                                }
                            
                            
                            
                            
                             int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                    
                                    
                                       int i =0;
                        while(i<a.getNbLines()){
                            
                                
                                
                                
                                
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run5(i,v,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            i++;
                        }
                            }else{
                                            if((a.getNbLines()==copie.size() || a.getNbLines()<copie.size()) && b.getNbLines()<copie.size()){
                                        
                                        
                                        
                                        
                                         int i =0;
                        while(i<a.getNbLines()){
                            
                                
                                
                                
                          
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run5(i,v,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            i++;
                        }
                        
                        
                        
                        
                         int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                            int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                               
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run5(j,v,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                                        
                                    }else{
                                                
                                                
                                                
                                                         if(a.getNbLines()<copie.size() && (b.getNbLines()==copie.size() || b.getNbLines()<copie.size())){
                                            
                                            
                                            
                                                              int i =0;
                        while(i<a.getNbLines()){
                            
                                
                                
                                
                               
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run5(i,v,"a",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            i++;
                        }
                        
                        
                        
                        
                         int g = 0;
                                    while(g<liste.size()){
                                        PeerID peerID=(PeerID) liste.get(g);
                                        
                                         copie.add(liste.get(g));
                   
                                            g++;
                   
                   
                                    }
                                    
                                    
                                            int j =0;
                        while(j<b.getNbLines()){
                            
                                
                                
                                
                               
                        
                         PeerID peerID=(PeerID) copie.getFirst();
                         
                            
                             myOutputPipe = workerPeer.createOutputPipetoPeerId(peerID);
                              
                               
                            workerPeer.run5(j,v,"b",myOutputPipe);
                            
                           
                            
                            copie.removeFirst();
                           
                                
                                
                                
                                
                           
                            
                            j++;
                        }
                                            
                                            
                                            
                                            
                                            
                                        }
                                                
                                            }
                                       }
                                      
                                  }
                             }
                        
                        
                        
                        
                            
                        }
                       
        
                            
                            
                   
                            }
                        }
    
    }
}
