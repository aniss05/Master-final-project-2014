package Master;



import Launcher.Frame_1;
import Launcher.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



import net.jxta.discovery.DiscoveryService;
import net.jxta.document.AdvertisementFactory;
import net.jxta.document.MimeMediaType;
import net.jxta.endpoint.Message;
import net.jxta.endpoint.MessageElement;
import net.jxta.endpoint.StringMessageElement;
import net.jxta.id.IDFactory;
//import net.jxta.impl.config.Config;
import net.jxta.peer.PeerID;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupID;
import net.jxta.pipe.InputPipe;
import net.jxta.pipe.OutputPipe;
import net.jxta.pipe.PipeID;
import net.jxta.pipe.PipeMsgEvent;
import net.jxta.pipe.PipeMsgListener;
import net.jxta.pipe.PipeService;
import net.jxta.platform.NetworkManager;

import net.jxta.protocol.PipeAdvertisement;
import org.apache.commons.codec.binary.Base64;
import org.jdesktop.swingx.JXComboBox;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author aniss
 */
public class MasterPeer extends Thread implements  PipeMsgListener{
    
    Scanner sc = new Scanner(System.in);

    // Iteration count
    int iterationCount = 19;
    public String str;
    public String chaine=null;
    int n,m,n1,n2;
    public Matrix a,b;
    private PeerGroup netPeerGroup=null;
    private DiscoveryService myDiscoveryService=null;
    private PipeAdvertisement pipeAdvertisement;
    private InputPipe myInputPipe,myInputPipe2;
    private int i,j;
    private String MatrixPath;
    private final static String SenderMessage="pipe_tutorial";
    private Matrix reMatrix;
    private Matrix reMatrix1;
    private int x1=0;
    private int y=0;
    private int z=0;
    private Cipher ecipher;
    public String k;
    public PeerID pid;
    private LinkedList list=new LinkedList();
    private LinkedList l=new LinkedList();
    public String PipeType;
    private static final String Name = "Hakim";
    private static final String Password = "hakimhakim";
    private static final PeerID peerID = IDFactory.newPeerID(PeerGroupID.defaultNetPeerGroupID,Name.getBytes());
    private static final String netPeerGroupName ="Jxta_Aniss_Hakim";
    public String filePath = "C:\\Users\\aniss\\Documents\\NetBeansProjects\\Matrices.txt";
    public PeerGroup peerGroup;
    public static final String ROUTEADV = "ROUTE";
    private PipeService myPipeService;
    private NetworkManager TNetworkManager;
    public Frame_1 frame_1 ;
    public final static int KEY_SIZE = 128;  // [32..448]
    private OutputPipe myOutputPipe;
    private List exists = Collections.synchronizedList(new LinkedList());
    private Date date1;
    private Date date2;
    private long T1,T2,T3;
    private ArrayList al = new ArrayList();
    private int index = 0;
    private double random;
    private double lower = -1000;
    private double higher = 1000;
    private JComboBox Choix = new JComboBox();
    
    
  
    public MasterPeer(PeerGroup nPeerGroup,NetworkManager myNetworkManager) throws FileNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException{
        
        this.TNetworkManager = myNetworkManager;
          
        this.netPeerGroup = nPeerGroup;
        myPipeService = netPeerGroup.getPipeService();
        
        
        
      
               Choix.addItem("Addition");
             Choix.addItem("Soustraction");
              Choix.addItem("Division");
               Choix.addItem("Multiplication");
                Choix.addItem("Transpose");
                 Choix.addItem("Multiplication par nombre");
     
        
        
        this.start();
         
        
        
        
    }
    
    
    public MasterPeer(LinkedList l, PeerGroup peerGroup1){
        this.list =  l;
        this.netPeerGroup = peerGroup1;
        
    }
    public MasterPeer(LinkedList l, NetworkManager myNetworkManager) throws IOException{
        this.l = l;
        this.TNetworkManager = myNetworkManager;
        this.netPeerGroup = TNetworkManager.getNetPeerGroup();
        
        
        int i = 0;
        while(i<l.size()){
            OutputPipe myOutputPipe = createOutputPipetoPeerId(l.get(i));
              Message myMessage=new Message();
                         
                         String chance = "Réexecuter";
                         myMessage.addMessageElement(null,new StringMessageElement(SenderMessage, chance, null));
                        
                         myOutputPipe.send(myMessage);
                         
                         i++;
                         
                         
            
        }
        this.l = null;
        TNetworkManager.stopNetwork();
        
        
        Launcher launcher = new Launcher();
        launcher.run();
        
      
        
    }

    

   
    public MasterPeer(LinkedList l,Matrix ax,Matrix bx,PeerGroup x,String chainex,NetworkManager myNetworkManager,InputPipe myInputPipe1,Frame_1 frame_11) throws IOException, InterruptedException, NoSuchAlgorithmException, NoSuchPaddingException{
        
      
      
        
       this.frame_1 = frame_11;
        this.myInputPipe = myInputPipe1;
        this.TNetworkManager = myNetworkManager;
       
        this.chaine=chainex;
        
        this.list=l;
        this.a=ax;
        this.b=bx;
        this.netPeerGroup=x;
        
        
       
           
        
           
           LinkedList copie = new LinkedList();
           int k = 0;
           while(k<list.size()){
               
                 
                  
               
                    copie.add(list.get(k));
                   
                   k++;
               
               
                   
                   
              
           }
           
           myInputPipe.close();
         
        
          this.getServices();
        
        
             try {            
            this.myInputPipe2 = this.creationInputPipe();
       
            
        } catch (Exception ex) {
            Logger.getLogger(MasterPeer.class.getName()).log(Level.SEVERE, null, ex);
        }
         
          
           
          
           
           
           
           Date date1 = new Date(System.currentTimeMillis());
           T1 =  date1.getTime();
            
             setKey(strPssword);
           
        Ordonnaceur ordonnaceur = new Ordonnaceur(a,b,chaine,list,this,netPeerGroup,myInputPipe2);
       
         if(chaine.equals("Addition") || chaine.equals("Soustraction") || chaine.equals("Division")){
             
             reMatrix = new Matrix(a.getNbLines(), a.getNbColumns());
              ordonnaceur.OrdonnancerAddition_Soustraction_Division();
              myInputPipe2.waitForMessage();
         
         }else{
             if(chaine.equals("Multiplication")){
                 reMatrix = new Matrix(a.getNbLines(), b.getNbColumns());
                  ordonnaceur.OrdonnancerMultiplication();
                  myInputPipe2.waitForMessage();
             }else{
                 
                 
                  if(chaine.equals("Transpose")){
                      
                       reMatrix = new Matrix(a.getNbColumns(), a.getNbLines());
                    reMatrix1 = new Matrix(b.getNbColumns(), b.getNbLines());
                    
                      ordonnaceur.OrdonnancerTranspose();
                      myInputPipe2.waitForMessage();
                      
                  }else{
                      if(chaine.equals("Multiplication par nombre")){
                          
                          reMatrix = new Matrix(a.getNbLines(), a.getNbColumns());
                        reMatrix1 = new Matrix(b.getNbLines(), b.getNbColumns());
                          
                          ordonnaceur.OrdonnancerMultiplication_par_nombre();
                          myInputPipe2.waitForMessage();
                      }
                  }
                  
                  
             }
             
             
         }
         
        
     
           
        
      
    }
    
  
    
    
   
    
  
    
   
    
    
    
    
 
        
        
        
        
     
    
    public void run(){
        try {        
            this.getServices();
        
        
             try {            
            this.myInputPipe = this.creationInputPipe();
       
            //myInputPipe.waitForMessage();
        } catch (Exception ex) {
            Logger.getLogger(MasterPeer.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            if(System.getProperty("os.name").contains("Windows")){
            
            getPath(new File("./"),"Matrix.txt");
            
        }else{
            if(System.getProperty("os.name").contains("Linux")){
                System.out.println("hello linux!!!");
                getPath(new File("/home/"),"Matrix.txt");
            }
        }
        
           

            
            
            Object[] message = new Object[ 10 ];
                    message[ 0 ] = "Nombre de lignes de a :";
                    message[ 1 ] = new JTextField();
                    message[ 2 ] = "Nombre de colomnes de a";
                    message[ 3 ] = new JTextField();
                     message[ 4 ] = "Nombre de lignes de b";
                     message[ 5 ] = new JTextField();
                     message[ 6 ] = "Nombre de colomnes de b";
                     message[ 7 ] = new JTextField();
                     message[ 8 ] = "Opération";
                     message[ 9 ] = Choix;
                     
 
                    String option[] = { "OK", "Annuler" };
                    
                    int result = JOptionPane.showOptionDialog(null,message,"connexion",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,message[1] );
                
                    if( result == 0 )
                {
                     a = new Matrix(Integer.parseInt(( (JTextField)message[ 1 ] ).getText()), Integer.parseInt(( (JTextField)message[ 3 ] ).getText()));
                     b = new Matrix(Integer.parseInt(( (JTextField)message[ 5 ] ).getText()), Integer.parseInt(( (JTextField)message[ 7 ] ).getText()));
                    
                    
                    chaine = ((JComboBox)message[ 9 ]).getSelectedItem().toString();
                    
  
                }else{
                    System.exit(0);
                }
                    
                    
           
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(al.get(0).toString()));
            bufferedWriter.write(chaine+";");
            bufferedWriter.newLine();
            bufferedWriter.write("Matrice a;");
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(a.getNbLines())+";");
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(a.getNbColumns())+";");
            bufferedWriter.newLine();
            
            
            int count1=4;
            while(count1<4+(a.getNbLines() * a.getNbColumns())){
                for(int i = 0; i<a.getNbLines(); i++){
                    for(int j = 0; j<a.getNbColumns(); j++){
                        random = (Math.random() * (higher-lower)) + lower;
                        bufferedWriter.write(String.valueOf(random)+";");
                        count1++;
                    }
                    bufferedWriter.newLine();
                }
            }
            
            bufferedWriter.write("Matrice b;");
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(b.getNbLines())+";");
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(b.getNbColumns())+";");
            
            bufferedWriter.newLine();
            
            int count2 = 4+a.getNbLines();
            
            int count3 = count2;
            
            while(count3<count2+(b.getNbLines() * b.getNbColumns())){
                for(int i = 0; i<b.getNbLines(); i++){
                    for(int j = 0; j<b.getNbColumns(); j++){
                        random = (Math.random() * (higher-lower)) + lower;
                        bufferedWriter.write(String.valueOf(random)+";");
                        count3++;
                    }
                    bufferedWriter.newLine();
                }
                
            }
            bufferedWriter.close();
            
           
            
            
            
            
              this.setMatrixPath(al.get(0).toString());
              
              String chString="";
              Scanner scanner=new Scanner(new File(al.get(0).toString()));

       

      while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          
          
          chString=chString+line;
          
          
         
              
      }

             

              
              
              
              String[] cString=chString.split(";");
               
              
              chaine = cString[0];
              System.out.println("Votre operation est "+chaine+" :");
              
              if(cString[1].equals("Matrice a")){
                   a=new Master.Matrix(Integer.parseInt(cString[2]), Integer.parseInt(cString[3]));
                  int k=4;
                  while(k<4+(a.getNbColumns()*a.getNbLines())){
                      for(int i=0; i<a.getNbLines();i++){
                          for(int j=0; j<a.getNbColumns();j++){
                              a.setTabMatrix(i, j, Double.parseDouble(cString[k]));
                              k++;
                             
                              
                          }
                      }
                      
                  }
                  System.out.println(cString[1]+" :");
                
                  a.showMatrix();
                  
              }
              int w=4+(Integer.parseInt(cString[2])*Integer.parseInt(cString[3]));
              
              if(cString[w].equals("Matrice b")){
                  
                  
                  b=new Master.Matrix(Integer.parseInt(cString[w+1]), Integer.parseInt(cString[w+2]));
                  int h=w+3;
                  while(h<cString.length){
                      for(int i=0; i<b.getNbLines();i++){
                          for(int j=0; j<b.getNbColumns();j++){
                              b.setTabMatrix(i, j, Double.parseDouble(cString[h]));
                              h++;
                              
                          }
                      }
                  }
                  
                  System.out.println(cString[w]+" :");
                  b.showMatrix();
                  
              }
       
                scanner.close();
                
              
              if(chaine.equals("Addition") || chaine.equals("Soustraction") || chaine.equals("Division") ){
                  reMatrix = new Matrix(a.getNbLines(), a.getNbColumns());
              }else{
                  if(chaine.equals("Multiplication")){
                      reMatrix = new Matrix(a.getNbLines(), b.getNbColumns());
                  }else{
                      if(chaine.equals("Transpose")){
                          reMatrix = new Matrix(a.getNbLines(), a.getNbColumns());
                          reMatrix1 = new Matrix(b.getNbLines(), b.getNbColumns());
                      }else{
                          if(chaine.equals("Multiplication par nombre")){
                              reMatrix = new Matrix(a.getNbLines(), a.getNbColumns());
                              reMatrix1 = new Matrix(b.getNbLines(), b.getNbColumns());
                          }
                      }
                  }
              }
              
       setKey(strPssword);
        Frame_1 frame_1 = new Frame_1(this, netPeerGroup, TNetworkManager,myInputPipe,al.get(0).toString());
frame_1.setVisible(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MasterPeer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MasterPeer.class.getName()).log(Level.SEVERE, null, ex);
        }

       
           
    }
    
    
    public void SetLinkedlist(LinkedList l){
       
        
        this.list=l;
        
        
    }
    
    
    
   
    
    
    public void getServices(){
        myDiscoveryService=netPeerGroup.getDiscoveryService();
        myPipeService=netPeerGroup.getPipeService();
    }
    
    
 
    
    
    
    
   
    
    
   
    
    
    
    
    public void SendTask(OutputPipe myOutputPipe,String str,Matrix a,Matrix b,int k) throws IOException {
        try {
            
            
            Message myMessage=new Message();
            
            String message0="";
            String message1=",";
            String message2=str;
            String message5="";
            String messagek=String.valueOf(k);
            
            if(str.contains("Addition") || str.contains("Soustraction") || str.contains("Division")){
                PeerID peerID=netPeerGroup.getPeerID();
                
                Tests tests=new Tests();
                if(tests.Test_Addition(a, b) || tests.Test_Soustraction(a, b) || tests.Test_Division(a, b)){
                    
                    
                                String message3=String.valueOf(k);
                
                String message4=String.valueOf(a.getNbColumns());
                
                
                
                    for(int j=0; j<a.getNbColumns(); j++){
                        message0=message0+String.valueOf(a.getTabMatrix()[k][j])+message1;
                    }
               
                
                
                
                
                    for(int j=0; j<b.getNbColumns(); j++){
                        message5=message5+String.valueOf(b.getTabMatrix()[k][j])+message1;
                    }
              
                
                
                String message=peerID+message1+message2+message1+message3+message1+message4+message1+messagek+message1+message0+message5;
                   
              
              
               
               String encryptedTask = encrypt(message);
               
                   
                    System.out.println("Task encrypted : "+encryptedTask);
                  
                myMessage.addMessageElement(null,new StringMessageElement(SenderMessage,encryptedTask , null));
                
                
                    
                    
             
                
                
                try {
                    
                    
                    
                    myOutputPipe.send(myMessage);
                    
                    
                   
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                }
                
                
                
                
            }
                
                        
                        
                    
                        
                    
                    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
        
        
        
        
    }
    
    
   
    
    
     public PipeAdvertisement GetPipeAdvertisement() {
    
        
        
    
        // Creating a Pipe Advertisement
        PipeAdvertisement MyPipeAdvertisement = (PipeAdvertisement) AdvertisementFactory.
                newAdvertisement(PipeAdvertisement.getAdvertisementType());
        PipeID MyPipeID = IDFactory.newPipeID(PeerGroupID.defaultNetPeerGroupID, Name.getBytes());
        MyPipeAdvertisement.setName("Test Pipe");
        
        
            PipeType= PipeService.PropagateType;
            MyPipeAdvertisement.setType(PipeType);
        
           
            
            
        
        MyPipeAdvertisement.setPipeID(MyPipeID);
        MyPipeAdvertisement.setDescription("Created by " + Name);
       
        
        return MyPipeAdvertisement;
        
    }

   
    
    
    
    
    
    
    
public OutputPipe createOutputPipetoPeerId(Object pid) throws FileNotFoundException {
     
     
   

      //pipe d'emission
      OutputPipe myPipe = null;
      
      
         PipeService pipeService = netPeerGroup.getPipeService();
      
      
      try {
           
           FileInputStream is = new FileInputStream("PropagatePipeAdv.xml");
    
    MimeMediaType asMimeMediaType = new MimeMediaType("text/xml");
        
         
         PipeAdvertisement mypipeAdvertisement= (PipeAdvertisement) AdvertisementFactory.newAdvertisement(asMimeMediaType.XMLUTF8, is);
          
        
        //creation de l'ensemble (a un seul element dans notre cas) des pairs pouvant recevoir les messages
        HashSet resolve = new HashSet();
        
        
        resolve.add(pid);
        
        

        //creation du pipe de communication
        
         
                 myPipe =pipeService.createOutputPipe(mypipeAdvertisement,resolve, 60000);
                
            //myPipe=myPipeService.createOutputPipe(pipeAdv, resolve, 60000);
        
       
          
         
        

      } catch (Exception e) {System.err.println("Erreur dans Client.creationPipe()");}

      return myPipe;

    }
 
 
 
 
 
 
 public InputPipe creationInputPipe() throws IOException{
     
    
          FileInputStream is= new FileInputStream("PropagatePipeAdv.xml");
          MimeMediaType asMimeMediaType=new MimeMediaType("text/xml");
          pipeAdvertisement=(PipeAdvertisement) AdvertisementFactory.newAdvertisement(asMimeMediaType.XMLUTF8,is);
            
      
          
           myDiscoveryService=netPeerGroup.getDiscoveryService();
           myDiscoveryService.publish(pipeAdvertisement,60000,60000);
           myDiscoveryService.remotePublish(pipeAdvertisement,(long) 60000);
           
           
      
           PipeService pipeService=netPeerGroup.getPipeService();
           InputPipe myInputPipe1=pipeService.createInputPipe(pipeAdvertisement,this);
           
           return myInputPipe1 ;
     
 }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    
    
    
    
    

    private void Decryptage(String xchaine) throws FileNotFoundException, IOException {
        
        
         
        
        String[] cString=xchaine.split(",");
       
            
            
            
            if(cString[2].equals("Addition") || cString[2].equals("Soustraction") || cString[2].equals("Division") ){
            
            
            
            
            
            
            int i=Integer.parseInt(cString[5]);
            
                 int k=6;
            while(k<cString.length){
               
            
            for(int j=0; j<reMatrix.getNbColumns(); j++){
                
                
           
                
                
                reMatrix.setTabMatrix(i, j, Double.parseDouble(cString[k]));
                
                k++;
                
                
                 
           
                
            }
                
                
                
            }
          
            x1++;
            if(x1==reMatrix.getNbLines()){
                
                
           
            
                
                
                
                Date date2 = new Date(System.currentTimeMillis());
                    
                    T2 = date2.getTime();
               
                           JOptionPane.showMessageDialog(null, "Votre tâche a duré :"+(T2 - T3), "Fin de tâche", JOptionPane.INFORMATION_MESSAGE);
                           
                           this.frame_1.set_reMatrix(reMatrix);
                
                
            }
            
           
            
            
            
        }else{
            if(cString[2].equals("Multiplication")){
                
                
                reMatrix.setTabMatrix(Integer.parseInt(cString[3]), Integer.parseInt(cString[4]), Double.parseDouble(cString[5]));
                x1++;
                
                if(x1==(reMatrix.getNbLines()*reMatrix.getNbColumns())){
                    reMatrix.showMatrix();
                     Date date2 = new Date(System.currentTimeMillis());
                    
                    T2 = date2.getTime();
               
                           JOptionPane.showMessageDialog(null, "Votre tâche a duré :"+(T2 - T3), "Fin de tâche", JOptionPane.INFORMATION_MESSAGE);
                             this.frame_1.set_reMatrix(reMatrix);
                }
                
            }else{
                if(cString[2].equals("Transpose")){
                    
                    
                    if(cString[3].equals("a")){
                        
                        
                        
                        int k=6;
                   
                    int j=Integer.parseInt(cString[5]);
                    while(k<cString.length){
                        
                         int i=0;
                    
                    
                    while(i<reMatrix.getNbLines()){
                        reMatrix.setTabMatrix(i, j, Double.parseDouble(cString[k]));
                        
                        k++;
                        i++;
                    }
                        
                        
                        
                    }
                    
                    y++;
                if(y==reMatrix.getNbColumns()){
                      this.frame_1.set_reMatrix(reMatrix);
                    
                    
                    reMatrix.showMatrix();
                 
                     
                }
                    
                }else{
                        if(cString[3].equals("b")){
                            
                            
                           
                            
                                int k=6;
                   
                    int j=Integer.parseInt(cString[5]);
                    while(k<cString.length){
                        
                         int i=0;
                    
                    
                    while(i<reMatrix1.getNbLines()){
                        reMatrix1.setTabMatrix(i, j, Double.parseDouble(cString[k]));
                        
                        k++;
                        i++;
                    }
                        
                        
                        
                    }
                    
                    z++;
                if(z==(reMatrix1.getNbColumns())){
                    System.out.println("Le résultat de votre "+cString[2]+" de la matrice "+cString[3]+" est :");
                   
                      this.frame_1.set_reMatrix1(reMatrix1);
                 
                      reMatrix1.showMatrix();
                     Date date2 = new Date(System.currentTimeMillis());
                    
                    T2 = date2.getTime();
               
                           JOptionPane.showMessageDialog(null, "Votre tâche a duré :"+(T2 - T3), "Fin de tâche", JOptionPane.INFORMATION_MESSAGE);
               
                }
                            
                            
                            
                            
                            
                   
                
                            
                            
                     }
                    }
                
                
                
                        
                    
                        
                    }else{
                    if(cString[2].equals("Multiplication par nombre")){
                        
                        
                        
                        
                        if(cString[3].equals("a")){
                        
                       
                        
                        int k=6;
                   
                    int i=Integer.parseInt(cString[4]);
                    while(k<cString.length){
                        
                         int j=0;
                    
                    
                    while(j<reMatrix.getNbColumns()){
                        reMatrix.setTabMatrix(i, j, Double.parseDouble(cString[k]));
                        
                        k++;
                        j++;
                    }
                        
                        
                        
                    }
                    
                    y++;
                if(y==reMatrix.getNbLines()){
                    System.out.println("Le résultat de votre "+cString[2]+" de la matrice "+cString[3]+" est :");
                 
                      this.frame_1.set_reMatrix(reMatrix);
                 
                }
                    
                }else{
                        if(cString[3].equals("b")){
                            
                            
                           
                            
                                int k=6;
                   
                    int i=Integer.parseInt(cString[4]);
                    while(k<cString.length){
                        
                         int j=0;
                    
                    
                    while(j<reMatrix1.getNbColumns()){
                        reMatrix1.setTabMatrix(i, j, Double.parseDouble(cString[k]));
                        
                        k++;
                        j++;
                    }
                        
                        
                        
                    }
                    
                    z++;
                if(z==(reMatrix1.getNbLines())){
                      this.frame_1.set_reMatrix1(reMatrix1);
                    
                     Date date2 = new Date(System.currentTimeMillis());
                    
                    T2 = date2.getTime();
               
                           JOptionPane.showMessageDialog(null, "Votre tâche a duré :"+(T2 - T3), "Fin de tâche", JOptionPane.INFORMATION_MESSAGE);
                    
                }
                            
                            
                            
                            
                            
                            
                            
                            
                     }
                    }
                        
                    }
                }
                    
                
            }
            
            
            
            
            
        }
      
            
        
        
        
        
       
        
        
        
        
       
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 

    public void run2(int i,OutputPipe myOutputPipe) {
        System.out.println("Démarrage du pipe de sortie");
        
        
        
         
        
            try{
              
                
                if(myOutputPipe!=null){
                    System.out.println("Pipe de sortie crée!!");
                    
                    
         
                    
                    SendTask(myOutputPipe, chaine, a, b,i);
                    
                    
                   
                     myInputPipe2.waitForMessage();
                    
                        
                    
                    
                }else{
                    System.out.println("bye!!!!!!!!!!");
                }
            }catch(Exception e){
                
                e.printStackTrace();
            }
        
    }
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    

    


@Override
   public synchronized void pipeMsgEvent(PipeMsgEvent pme) {
    
    
        Message message=null;
        
        try{
            message=pme.getMessage();
            
            if(message==null){
                System.out.println("null !!");
            }else{
                
                
                
                MessageElement myMessageElement=message.getMessageElement(null,SenderMessage);
                
                String chaine=myMessageElement.toString();
                
                System.out.println("Received message encrypted : "+chaine);
               
                String decrypted = decrypt(chaine);
                
                
                
                if(decrypted.contains("Result")){
                    
                    System.out.println("Decrypted result : "+decrypted);
                    if(!exists.contains(decrypted)){
                        
                         exists.add(decrypted);
                   
                    
                   
                    Decryptage(decrypted);
                        
                    }
                   
                    
                }else{
                    System.out.println("Decrypted task : "+decrypted);
                    if(!exists.contains(decrypted)){
                        exists.add(decrypted);
                        
                        
                       
                        Decryptage1(decrypted);
                    }else{
                         exists.remove(decrypted);
                       
                       Decryptage1(decrypted);
                       
                       exists.add(decrypted);
                    }
                    
                }
                
                
                
                
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }



private void Decryptage1(String chaine) throws IOException {
        
        String[] cString=chaine.split(",");
        
         
             
             if(cString[1].equals("Addition")){
                 
               
                
                     if(Integer.parseInt(cString[2])==0){
                
                
                
                
                
                   Matrix a=new Matrix(1, Integer.parseInt(cString[3]));
            
            Matrix b=new Matrix(1, Integer.parseInt(cString[3]));
           
            int k=5;
            
            
            while(k<5+(Integer.parseInt(cString[2]+1)*Integer.parseInt(cString[3]))){
                for(int i=0; i<a.getNbLines(); i++){
                
                    for(int j=0; j<a.getNbColumns(); j++){
                    
                    
                        
                        a.setTabMatrix(i, j, Double.parseDouble(cString[k]));
                        
                        k++;
                        
                    }
                }
                
            }
            int h=5+(Integer.parseInt(cString[2]+1)*Integer.parseInt(cString[3]));
            
                
            while(h<cString.length){
                for(int i=0; i<b.getNbLines(); i++){
                    for(int j=0; j<b.getNbColumns(); j++){
                    
                    
                        
                        b.setTabMatrix(i, j, Double.parseDouble(cString[h]));
                        h++;
                        
                    }
                }
            }
            //a.showMatrix(chaine, a);
            //b.showMatrix(chaine, a);
            
           
                
                         Master.Addition add = new Master.Addition(a, b);
                   Matrix reMatrix=add.Add(a, b);
                
           
            
            
                         
              
            
            
            //reMatrix.showMatrix(chaine, a);
                         
            int lisst=0;
            while(lisst<list.size()){
                String xc = list.get(lisst).toString();
                  if(!xc.equals(cString[0])){
                      
                    lisst++;
                    
                }
                else{
                     
                    PeerID pd=(PeerID) list.get(lisst);
                   
                     myOutputPipe=createOutputPipetoPeerId(pd);
             //loadPipeAdv();
                      
            SendResult(myOutputPipe, reMatrix, Integer.parseInt(cString[4]),"Result",cString[1]);
            
            
            lisst++;
                }
                  
                
            }
            
            
              
           
           
            
            
                
                
            }else{
                
                
                
                Matrix a=new Matrix(1, Integer.parseInt(cString[3]));
            
            Matrix b=new Matrix(1, Integer.parseInt(cString[3]));
           
            int k=5;
            
            
            while(k<5+(1*Integer.parseInt(cString[3]))){
                for(int i=0; i<a.getNbLines(); i++){
                
                    for(int j=0; j<a.getNbColumns(); j++){
                    
                    
                        
                        a.setTabMatrix(i, j, Double.parseDouble(cString[k]));
                        
                        k++;
                        
                    }
                }
                
            }
            int h=5+(1*Integer.parseInt(cString[3]));
            
            
            while(h<cString.length){
                for(int i=0; i<b.getNbLines(); i++){
                    for(int j=0; j<b.getNbColumns(); j++){
                    
                    
                        
                        b.setTabMatrix(i, j, Double.parseDouble(cString[h]));
                        h++;
                        
                    }
                }
            }
            //a.showMatrix(chaine, a);
            //b.showMatrix(chaine, a);
            
            
            
            
                
                         Master.Addition add=new Master.Addition(a, b);
            Matrix reMatrix=add.Add(a, b);
            
            
            //reMatrix.showMatrix(chaine, a);
           int lit=0;
            while(lit<list.size()){
                 String xc = list.get(lit).toString();
                  if(!xc.equals(cString[0])){
                      
                    lit++;
                    
              
                }
                else{
                    PeerID pd=(PeerID) list.get(lit);
                    
                     myOutputPipe=createOutputPipetoPeerId(pd);
                     
             //loadPipeAdv();
            SendResult(myOutputPipe, reMatrix, Integer.parseInt(cString[4]),"Result",cString[1]);
            
            lit++;
                }
                    
            }
          
            
                
            
                
                
                
                
                
                
                
                
            }
                    
                
            
            
            
            
            
            
        }else{
            if(cString[1].equals("Soustraction")){
                
                
               if(Integer.parseInt(cString[2])==0){
                
                
                
                
                
                   Matrix a=new Matrix(1, Integer.parseInt(cString[3]));
            
            Matrix b=new Matrix(1, Integer.parseInt(cString[3]));
           
            int k=5;
            
            
            while(k<5+(Integer.parseInt(cString[2]+1)*Integer.parseInt(cString[3]))){
                for(int i=0; i<a.getNbLines(); i++){
                
                    for(int j=0; j<a.getNbColumns(); j++){
                    
                    
                        
                        a.setTabMatrix(i, j, Double.parseDouble(cString[k]));
                        
                        k++;
                        
                    }
                }
                
            }
            int h=5+(Integer.parseInt(cString[2]+1)*Integer.parseInt(cString[3]));
            
                
            while(h<cString.length){
                for(int i=0; i<b.getNbLines(); i++){
                    for(int j=0; j<b.getNbColumns(); j++){
                    
                    
                        
                        b.setTabMatrix(i, j, Double.parseDouble(cString[h]));
                        h++;
                        
                    }
                }
            }
            //a.showMatrix(chaine, a);
            //b.showMatrix(chaine, a);
            
           
                
                   Master.Soustraction sous = new Master.Soustraction(a, b);
                 Matrix reMatrix = sous.Sous(a, b);
                 
                
                
           
            int lit=0;
            while(lit<list.size()){
                 String xc = list.get(lit).toString();
                  if(!xc.equals(cString[0])){
                      
                    lit++;
                    
              
                }
                else{
                    PeerID pd=(PeerID) list.get(lit);
                    
                     myOutputPipe=createOutputPipetoPeerId(pd);
             //loadPipeAdv();
            SendResult(myOutputPipe, reMatrix, Integer.parseInt(cString[4]),"Result",cString[1]);
            lit++;
                }
                    
            }
            
            
                
              
            
            
          
                
                
            }else{
                
                
                
                Matrix a=new Matrix(1, Integer.parseInt(cString[3]));
            
            Matrix b=new Matrix(1, Integer.parseInt(cString[3]));
           
            int k=5;
            
            
            while(k<5+(1*Integer.parseInt(cString[3]))){
                for(int i=0; i<a.getNbLines(); i++){
                
                    for(int j=0; j<a.getNbColumns(); j++){
                    
                    
                        
                        a.setTabMatrix(i, j, Double.parseDouble(cString[k]));
                        
                        k++;
                        
                    }
                }
                
            }
            int h=5+(1*Integer.parseInt(cString[3]));
            
            
            while(h<cString.length){
                for(int i=0; i<b.getNbLines(); i++){
                    for(int j=0; j<b.getNbColumns(); j++){
                    
                    
                        
                        b.setTabMatrix(i, j, Double.parseDouble(cString[h]));
                        h++;
                        
                    }
                }
            }
            //a.showMatrix(chaine, a);
            //b.showMatrix(chaine, a);
            
            
            
            
                
                   Master.Soustraction sous = new Master.Soustraction(a, b);
              Matrix reMatrix = sous.Sous(a, b);
              
              
              
            
            int lisst=0;
            while(lisst<list.size()){
                String xc = list.get(lisst).toString();
                  if(!xc.equals(cString[0])){
                      
                    lisst++;
                    
                }
                else{
                     
                    PeerID pd=(PeerID) list.get(lisst);
                   
                     myOutputPipe=createOutputPipetoPeerId(pd);
             //loadPipeAdv();
            SendResult(myOutputPipe, reMatrix, Integer.parseInt(cString[4]),"Result",cString[1]);
            lisst++;
                }
                  
                
            }
            
                
            
                
                
                
                
                
                
                
                
            }
                    
                
            
            
            
            
            
                
            }else{
                if(cString[1].equals("Multiplication")){
                    
                    
                    Matrix a = new Matrix(1, Integer.parseInt(cString[4]));
                    Matrix b = new Matrix(Integer.parseInt(cString[5]), 1);
                    
                    
                    int k = 6;
                    
                    while(k< 6+(1*Integer.parseInt(cString[4]))){
                        for(int j =0; j<a.getNbColumns();j++){
                            a.setTabMatrix(0, j, Double.parseDouble(cString[k]));
                            
                            k++;
                            
                        }
                    }
                    
                    int h=6+(1*Integer.parseInt(cString[4]));
                    
                    while(h<cString.length){
                        for(int i=0; i<b.getNbLines();i++){
                            b.setTabMatrix(i, 0, Double.parseDouble(cString[h]));
                            h++;
                        }
                    }
                    
                    
                    
                    Master.Multiplication multiplication = new Master.Multiplication(a, b);
                    Matrix reMatrix = multiplication.Mul(a, b);
                    
                    /*loadPipeAdv();
                    SendResult1(myOutputPipe, reMatrix, Integer.parseInt(cString[1]),Integer.parseInt(cString[2]),"Result",cString[0]);*/
                    
                    
                    
                     int lisst=0;
            while(lisst<list.size()){
                String xc = list.get(lisst).toString();
                  if(!xc.equals(cString[0])){
                      
                    lisst++;
                    
                }
                else{
                     
                    PeerID pd=(PeerID) list.get(lisst);
                   
                     myOutputPipe=createOutputPipetoPeerId(pd);
             //loadPipeAdv();
            //SendResult(myOutputPipe, reMatrix, Integer.parseInt(cString[4]),"Result",cString[1]);
                      
                     SendResult1(myOutputPipe, reMatrix, Integer.parseInt(cString[2]),Integer.parseInt(cString[3]),"Result",cString[1]);
                        
            lisst++;
                }
                  
                
            }
                    
                    
                    
                    
                }else{
                    if(cString[1].equals("Transpose")){
                        if(cString[2].equals("a")){
                            Matrix a = new Matrix(1, Integer.parseInt(cString[4]));
                            
                            int h=5;
                            
                            while(h<cString.length){
                                
                                for(int j=0; j<a.getNbColumns();j++){
                                    a.setTabMatrix(0, j, Double.parseDouble(cString[h]));
                                    h++;
                                    
                                }
                                
                            }
                            
                            
                            Master.Transpose transpose = new Master.Transpose(a);
                           Matrix reMatrix = transpose.transpose(a);
                            
                            
                                    int lisst=0;
            while(lisst<list.size()){
                String xc = list.get(lisst).toString();
                  if(!xc.equals(cString[0])){
                      
                    lisst++;
                    
                }
                else{
                     
                    PeerID pd=(PeerID) list.get(lisst);
                   
                     myOutputPipe=createOutputPipetoPeerId(pd);
             //loadPipeAdv();
            //SendResult(myOutputPipe, reMatrix, Integer.parseInt(cString[4]),"Result",cString[1]);
                     //SendResult2(myOutputPipe, reMatrix, Integer.parseInt(cString[1]),Integer.parseInt(cString[2]),"Result",cString[0]);
                      SendResult2(myOutputPipe, reMatrix, Integer.parseInt(cString[4]),Integer.parseInt(cString[3]),"Result",cString[2],cString[1]);
            lisst++;
                }
                  
                
            }
                            
                    
                            
                        }else{
                            if(cString[2].equals("b")){
                                
                                
                                
                                Matrix b = new Matrix(1, Integer.parseInt(cString[4]));
                            
                            int h=5;
                            
                            while(h<cString.length){
                                
                                for(int j=0; j<b.getNbColumns();j++){
                                    b.setTabMatrix(0, j, Double.parseDouble(cString[h]));
                                    h++;
                                    
                                }
                                
                            }
                            
                            
                                Master.Transpose transpose = new Master.Transpose(b);
                            Matrix reMatrix = transpose.transpose(b);
                            
                            
                                                    int lisst=0;
            while(lisst<list.size()){
                String xc = list.get(lisst).toString();
                  if(!xc.equals(cString[0])){
                      
                    lisst++;
                    
                }
                else{
                     
                    PeerID pd=(PeerID) list.get(lisst);
                   
                     myOutputPipe=createOutputPipetoPeerId(pd);
             //loadPipeAdv();
            //SendResult(myOutputPipe, reMatrix, Integer.parseInt(cString[4]),"Result",cString[1]);
                     //SendResult2(myOutputPipe, reMatrix, Integer.parseInt(cString[1]),Integer.parseInt(cString[2]),"Result",cString[0]);
                      SendResult2(myOutputPipe, reMatrix, Integer.parseInt(cString[4]),Integer.parseInt(cString[3]),"Result",cString[2],cString[1]);
            lisst++;
                }
                  
                
            }
                                
                            }
                        }
                    }else{
                        if(cString[1].equals("Division")){
                            
                            
                                         if(Integer.parseInt(cString[2])==0){
                
                
                
                
                
                             Matrix a=new Matrix(1, Integer.parseInt(cString[3]));
            
            Matrix b=new Matrix(1, Integer.parseInt(cString[3]));
           
            int k=5;
            
            
            while(k<5+(Integer.parseInt(cString[2]+1)*Integer.parseInt(cString[3]))){
                for(int i=0; i<a.getNbLines(); i++){
                
                    for(int j=0; j<a.getNbColumns(); j++){
                    
                    
                        
                        a.setTabMatrix(i, j, Double.parseDouble(cString[k]));
                        
                        k++;
                        
                    }
                }
                
            }
            int h=5+(Integer.parseInt(cString[2]+1)*Integer.parseInt(cString[3]));
            
                
            while(h<cString.length){
                for(int i=0; i<b.getNbLines(); i++){
                    for(int j=0; j<b.getNbColumns(); j++){
                    
                    
                        
                        b.setTabMatrix(i, j, Double.parseDouble(cString[h]));
                        h++;
                        
                    }
                }
            }
            //a.showMatrix(chaine, a);
            //b.showMatrix(chaine, a);
            
           
                
                                             Master.Division div=new Master.Division(a, b);
                  Matrix reMatrix=div.Div(a, b);
                
           
            
            
                
              
            
            
          int lisst=0;
            while(lisst<list.size()){
                String xc = list.get(lisst).toString();
                  if(!xc.equals(cString[0])){
                      
                    lisst++;
                    
                }
                else{
                     
                    PeerID pd=(PeerID) list.get(lisst);
                   
                     myOutputPipe=createOutputPipetoPeerId(pd);
             //loadPipeAdv();
            SendResult(myOutputPipe, reMatrix, Integer.parseInt(cString[4]),"Result",cString[1]);
            lisst++;
                }
                  
                
            }
                
                
            }else{
                
                
                
                Matrix a=new Matrix(1, Integer.parseInt(cString[3]));
            
          Matrix b=new Matrix(1, Integer.parseInt(cString[3]));
           
            int k=5;
            
            
            while(k<5+(1*Integer.parseInt(cString[3]))){
                for(int i=0; i<a.getNbLines(); i++){
                
                    for(int j=0; j<a.getNbColumns(); j++){
                    
                    
                        
                        a.setTabMatrix(i, j, Double.parseDouble(cString[k]));
                        
                        k++;
                        
                    }
                }
                
            }
            int h=5+(1*Integer.parseInt(cString[3]));
            
            
            while(h<cString.length){
                for(int i=0; i<b.getNbLines(); i++){
                    for(int j=0; j<b.getNbColumns(); j++){
                    
                    
                        
                        b.setTabMatrix(i, j, Double.parseDouble(cString[h]));
                        h++;
                        
                    }
                }
            }
            //a.showMatrix(chaine, a);
            //b.showMatrix(chaine, a);
            
            
            
            
                
                                             Master.Division division = new Master.Division(a, b);
               Matrix reMatrix = division.Div(a, b);
            
            
           int lisst=0;
            while(lisst<list.size()){
                String xc = list.get(lisst).toString();
                  if(!xc.equals(cString[0])){
                      
                    lisst++;
                    
                }
                else{
                     
                    PeerID pd=(PeerID) list.get(lisst);
                   
                     myOutputPipe=createOutputPipetoPeerId(pd);
             //loadPipeAdv();
            SendResult(myOutputPipe, reMatrix, Integer.parseInt(cString[4]),"Result",cString[1]);
            lisst++;
                }
                  
                
            }
            
                
                
                
                
                
                
                
                
            }
                    
                
            
            
            
            
            
                            
                            
                            
                            
                        }else{
                            if(cString[1].equals("Multiplication par nombre")){
                                
                                if(cString[2].equals("a")){
                                    
                                    
                                    
                                     Matrix a = new Matrix(1, Integer.parseInt(cString[4]));
                            
                            int h=6;
                            
                            while(h<cString.length){
                                
                                for(int j=0; j<a.getNbColumns();j++){
                                    a.setTabMatrix(0, j, Double.parseDouble(cString[h]));
                                    h++;
                                    
                                }
                                
                            }
                            
                            
                                    Master.MultiplicationNombre multiplicationNombre = new Master.MultiplicationNombre(a,Integer.parseInt(cString[5]));
                            Matrix reMatrix = multiplicationNombre.MultiplicationParNombre(a, Integer.parseInt(cString[5]));
                            
                            
                            int lisst=0;
            while(lisst<list.size()){
                String xc = list.get(lisst).toString();
                  if(!xc.equals(cString[0])){
                      
                   lisst++;
                }
                else{
                     
                    PeerID pd=(PeerID) list.get(lisst);
                   
                     myOutputPipe=createOutputPipetoPeerId(pd);
             //loadPipeAdv();
            //SendResult(myOutputPipe, reMatrix, Integer.parseInt(cString[4]),"Result",cString[1]);
                     SendResult2(myOutputPipe, reMatrix, Integer.parseInt(cString[3]),Integer.parseInt(cString[4]),"Result",cString[2],cString[1]);
            lisst++;
                }
                  
                
            }
                             
                    
                            
                        }else{
                            if(cString[2].equals("b")){
                                
                                
                                
                                Matrix b = new Matrix(1, Integer.parseInt(cString[4]));
                            
                            int h=6;
                            
                            while(h<cString.length){
                                
                                for(int j=0; j<b.getNbColumns();j++){
                                    b.setTabMatrix(0, j, Double.parseDouble(cString[h]));
                                    h++;
                                    
                                }
                                
                            }
                            
                            
                                Master.MultiplicationNombre multiplicationNombre = new Master.MultiplicationNombre(b, Integer.parseInt(cString[5]));
                            Matrix reMatrix = multiplicationNombre.MultiplicationParNombre(b, Integer.parseInt(cString[5]));
                            
                                           int lisst=0;
            while(lisst<list.size()){
                String xc = list.get(lisst).toString();
                  if(!xc.equals(cString[0])){
                      
                    lisst++;
                    
                }
                else{
                     
                    PeerID pd=(PeerID) list.get(lisst);
                   
                     myOutputPipe=createOutputPipetoPeerId(pd);
             //loadPipeAdv();
            //SendResult(myOutputPipe, reMatrix, Integer.parseInt(cString[4]),"Result",cString[1]);
                     SendResult2(myOutputPipe, reMatrix, Integer.parseInt(cString[3]),Integer.parseInt(cString[4]),"Result",cString[2],cString[1]);
            lisst++;
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
    

   

    public void run3(int i, int j, OutputPipe myOutputPipe) {
        System.out.println("Démarrage du pipe de sortie");
        
        
        
        
            try{
              
                
                if(myOutputPipe!=null){
                    System.out.println("Pipe de sortie crée!!");
                    
                    
                  
         
                    
                    SendTask1(myOutputPipe, chaine, a, b,i,j);
                    
                    
                   
                     myInputPipe2.waitForMessage();
                    
                        
                    
                    
                }else{
                    System.out.println("bye!!!!!!!!!!");
                }
            }catch(Exception e){
                
                e.printStackTrace();
            }
    }

    private void SendTask1(OutputPipe myOutputPipe, String chaine, Matrix a, Matrix b, int k, int h) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, UnknownHostException, SocketException {
        
        
        Message myMessage=new Message();
            
            String message0="";
            String message1=",";
            String message2=chaine;
            String message5="";
            String messagek=String.valueOf(k);
            String messageh=String.valueOf(h);
        
        
        if(chaine.contains("Multiplication")){
            
            
                    
                    Tests tests=new Tests();
                    if(tests.Test_Multiplication(a, b)){
                        
                        for(int j =0; j<a.getNbColumns();j++){
                            
                            message0=message0+String.valueOf(a.getTabMatrix()[k][j])+message1;
                            
                        }
                        
                        for( int i = 0; i < b.getNbLines(); i++){
                            message5=message5+String.valueOf(b.getTabMatrix()[i][h])+message1;
                        }
                        
                        String messString = netPeerGroup.getPeerID()+message1+chaine+message1+messagek+message1+messageh+message1+String.valueOf(a.getNbColumns())+message1+String.valueOf(b.getNbLines())+message1+message0+message5;
                        
                    
                        
                        String cryptedTask = encrypt(messString);
                        
                         System.out.println("Task encrypted : "+cryptedTask);
                         myMessage.addMessageElement(null,new StringMessageElement(SenderMessage, cryptedTask, null));
                
                
                    
                    
                
                
                
                try {
                    
                  
                    
                    myOutputPipe.send(myMessage);
                    
                    
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                        
                        
                    }
         }
            
            
        
                       
            }

    public void run4(int i, String x, OutputPipe myOutputPipe) {
        
        System.out.println("Démarrage du pipe de sortie");
        
        
        
        
            try{
              
                
                if(myOutputPipe!=null){
                    System.out.println("Pipe de sortie crée!!");
                    
                    
                  
         
                    
                    SendTask2(myOutputPipe, chaine, a, b,i,x);
                    
                    
                   
                     myInputPipe2.waitForMessage();
                    
                        
                    
                    
                }else{
                    System.out.println("bye!!!!!!!!!!");
                }
            }catch(Exception e){
                
                e.printStackTrace();
            }
        
        
       
    }

    private void SendTask2(OutputPipe myOutputPipe, String chaine, Matrix a, Matrix b, int k, String x) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, UnknownHostException, SocketException {
        
        Message myMessage=new Message();
        
        String message0="";
        String message1=",";
        String message2=chaine;
        String message5="";
        String messagek=String.valueOf(k);
        
        
        
        if(x=="a"){
            
            for(int j=0; j<a.getNbColumns(); j++){
                message0=message0+String.valueOf(a.getTabMatrix()[k][j])+message1;
            }
            
            
            String messString=netPeerGroup.getPeerID()+message1+chaine+message1+x+message1+messagek+message1+String.valueOf(a.getNbColumns())+message1+message0;
            
            
           String encryptedTask = encrypt(messString);
           
           System.out.println("Task encrypted 1 : "+encryptedTask);
            
            myMessage.addMessageElement(null,new StringMessageElement(SenderMessage, encryptedTask, null));
                
                
                    
                    
                
                
                
                try {
                    
                  
                    
                    myOutputPipe.send(myMessage);
                    
                    
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                        
            
        }else{
            if(x=="b"){
                
                
                
                
                
                for(int j=0; j<b.getNbColumns(); j++){
                message5=message5+String.valueOf(b.getTabMatrix()[k][j])+message1;
            }
            
            
            String messString=netPeerGroup.getPeerID()+message1+chaine+message1+x+message1+messagek+message1+String.valueOf(b.getNbColumns())+message1+message5;
            
            
            String encryptedTask = encrypt(messString);
            
            System.out.println("Task encrypted 2 : "+encryptedTask);
            
            myMessage.addMessageElement(null,new StringMessageElement(SenderMessage, encryptedTask, null));
                
                
                    
                    
                
                
                
                try {
                    
                  
                    
                    myOutputPipe.send(myMessage);
                    
                    
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                     
                
            }
        }
        
    }

    public void run5(int i, int v, String s, OutputPipe myOutputPipe) {
        System.out.println("Démarrage du pipe de sortie");
        
        
        
        
            try{
              
                
                if(myOutputPipe!=null){
                    System.out.println("Pipe de sortie crée!!");
                    
                    
                  
         
                    
                    SendTask3(myOutputPipe, chaine, a, b,i,v,s);
                    
                    
                   
                     myInputPipe2.waitForMessage();
                    
                        
                    
                    
                }else{
                    System.out.println("bye!!!!!!!!!!");
                }
            }catch(Exception e){
                
                e.printStackTrace();
            }
        
    }

    private void SendTask3(OutputPipe myOutputPipe, String chaine, Matrix a, Matrix b, int k, int v, String s) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, UnknownHostException, SocketException {
        
        Message myMessage = new Message();
        
        String message0="";
        String message1=",";
        String message2=String.valueOf(a.getNbColumns());
        String message3=String.valueOf(b.getNbColumns());
        String messagek=String.valueOf(k);
        String messagev=String.valueOf(v);
        String message5="";
        
        
        if(s=="a"){
            
            
            for(int j=0; j<a.getNbColumns(); j++){
                message0=message0+String.valueOf(a.getTabMatrix()[k][j])+message1;
            }
            
            String message = netPeerGroup.getPeerID()+message1+chaine+message1+s+message1+messagek+message1+message2+message1+messagev+message1+message0;
            
            String encryptedTask = encrypt(message);
            
            System.out.println("Task encrypted 1 : "+encryptedTask);
            
            myMessage.addMessageElement(null,new StringMessageElement(SenderMessage, encryptedTask, null));
                
                
                
                    
                
                
                
                try {
                    
                    
                    
                    myOutputPipe.send(myMessage);
                    
                    
                    
                    
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            
            
            
        }else{
            
            if(s=="b"){
                
                
                
                
            for(int j=0; j<b.getNbColumns(); j++){
                message5=message5+String.valueOf(b.getTabMatrix()[k][j])+message1;
            }
            
            String message = netPeerGroup.getPeerID()+message1+chaine+message1+s+message1+messagek+message1+message3+message1+messagev+message1+message5;
            
            String encryptedTask = encrypt(message);
            
            System.out.println("Task encrypted 2 : "+encryptedTask);
            myMessage.addMessageElement(null,new StringMessageElement(SenderMessage, encryptedTask, null));
                
                
                    
                    
                
                
                
                try {
                    
                  
                     
                    myOutputPipe.send(myMessage);
                   
                    
                    
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            
            
                
            }
        }
    }

 

    public OutputPipe CreateOutputPipe() throws FileNotFoundException, IOException {
         FileInputStream is= new FileInputStream("PropagatePipeAdv.xml");
          MimeMediaType asMimeMediaType=new MimeMediaType("text/xml");
          pipeAdvertisement=(PipeAdvertisement) AdvertisementFactory.newAdvertisement(asMimeMediaType.XMLUTF8,is);
          
      
          
           myDiscoveryService=netPeerGroup.getDiscoveryService();
           myDiscoveryService.publish(pipeAdvertisement,60000,60000);
           myDiscoveryService.remotePublish(pipeAdvertisement,(long) 60000);
           
           
      
           PipeService pipeService=netPeerGroup.getPipeService();
           OutputPipe myOutputPipe;
        myOutputPipe = pipeService.createOutputPipe(pipeAdvertisement, 60000);
        
        return  myOutputPipe;
    }

    

    public void loadPipeAdv() throws FileNotFoundException, IOException {

      //chargement de l'annonce depuis un fichier
      FileInputStream file = new FileInputStream("PropagatePipeAdv.xml");
      MimeMediaType asMimeType = new MimeMediaType("text/xml");

      //il s'agit d'un pipe de diffusion (multicast)
      PipeAdvertisement pipeAdv = (PipeAdvertisement) AdvertisementFactory.newAdvertisement(asMimeType.XMLUTF8,file);

      //creation du pipe de communication
      PipeService pipeService = netPeerGroup.getPipeService();
      myOutputPipe = pipeService.createOutputPipe(pipeAdv,60000);

    }
    
    
     public void SendResult(OutputPipe myOutputPipe,Matrix a,int k,String mString,String op){
       
       
        try {
            
                 Message myMessage=new Message();
                 
                 String message0="";
                 String message1=",";
                 String messagek=String.valueOf(k);
                 String name=netPeerGroup.getPeerName();
                 
                 String message3=String.valueOf(a.getNbLines());
                
                String message4=String.valueOf(a.getNbColumns());
                
                
                
                for(int i=0; i<a.getNbLines(); i++){
                    for(int j=0; j<a.getNbColumns(); j++){
                        message0=message0+String.valueOf(a.getTabMatrix()[i][j])+message1;
                    }
                }
                
                String message=mString+message1+name+message1+op+message1+message3+message1+message4+message1+messagek+message1+message0;
                
                
                
                String encryptedResult = encrypt(message);
                
               System.out.println("Encrypted result : "+encryptedResult);
               
               myMessage.addMessageElement(null,new StringMessageElement(SenderMessage, encryptedResult, null));
                
             
                
               
                try {
                    
                   
                    
                    myOutputPipe.send(myMessage);
                    
                    
                    
                    
                    
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                 
                 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       
   }
     
     
     private void SendResult1(OutputPipe myOutputPipe, Matrix reMatrix, int parseInt, int parseInt0, String result, String op) {
        
        Message myMessage = new Message();
        String name = netPeerGroup.getPeerName();
 
        
        String message0="";
        String message1=",";
        String message2=String.valueOf(parseInt);
        String message3=String.valueOf(parseInt0);
        String message4="";

                message4=message4+String.valueOf(reMatrix.getTabMatrix()[0][0])+message1;
                
            
        
        
        message0=result+message1+name+message1+op+message1+message2+message1+message3+message1+message4;
        
        
        String cryptedResult = encrypt(message0);
        
        System.out.println("Encrypted result : "+cryptedResult);
           myMessage.addMessageElement(null,new StringMessageElement(SenderMessage, cryptedResult, null));
                
                
                
               
                try {
                    
                    
                    
                    myOutputPipe.send(myMessage);
                    
                    
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
    }
     
     private void SendResult2(OutputPipe myOutputPipe, Matrix reMatrix, int k, int h, String result, String string,String op) {
        
        Message myMessage = new Message();
        
        
        
        
        String name=netPeerGroup.getPeerName();
        String message0="";
        String message1=",";
        String messagek=String.valueOf(k);
        String messageh=String.valueOf(h);
      
            
            
            
            for(int i=0; i<reMatrix.getNbLines(); i++){
            for(int j=0; j<reMatrix.getNbColumns(); j++){
                message0=message0+String.valueOf(reMatrix.getTabMatrix()[i][j])+message1;
            }
        }
            
            String messString=result+message1+name+message1+op+message1+string+message1+String.valueOf(k)+message1+String.valueOf(h)+message1+message0;
            
            String encryptedResult = encrypt(messString);
            
            System.out.println("Encrypted result : "+encryptedResult);
            
            myMessage.addMessageElement(null,new StringMessageElement(SenderMessage, encryptedResult, null));
                
                
                
               
                try {
                    
                    
                    
                    myOutputPipe.send(myMessage);
                    
                    
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            
      
        
        
        
        
        
    }

     
     
 public Matrix GetResult() {
       return this.reMatrix;
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
 
  final String strPssword = "0551468062";
  private static SecretKeySpec secretKey ;
private static byte[] key ;
    private static String decryptedString;
    private static String encryptedString;
    
    
public void setKey(String myKey){
    
    MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
           
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
         key = Arrays.copyOf(key, 16); // use only first 128 bit
            System.out.println("Votre clé est : "+key+" et sa taille est : "+key.length);
         secretKey = new SecretKeySpec(key, "AES");
        
        
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
         
    
}
public  String getDecryptedString() {
        return decryptedString;
    }
    public  void setDecryptedString(String decryptedString) {
        this.decryptedString = decryptedString;
    }
public  String getEncryptedString() {
        return this.encryptedString;
    }
    public  void setEncryptedString(String encryptedString) {
        this.encryptedString = encryptedString;
    }
    public String encrypt(String strToEncrypt)
{
try
{
Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
cipher.init(Cipher.ENCRYPT_MODE, secretKey);
return Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
}
catch (Exception e)
{
System.out.println("Error while encrypting: "+e.toString());
return null;
}

}
public  String decrypt(String strToDecrypt)
{
try
{
Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
cipher.init(Cipher.DECRYPT_MODE, secretKey);
return new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
}
catch (Exception e)
{
System.out.println("Error while decrypting: "+e.toString());
return null;
}

}

 private void setMatrixPath(String toString) {
        this.MatrixPath = toString;
    }

    public String getMatrixPath() {
        return this.MatrixPath;
    }

  
 
}
    
    

  

    

    


    

