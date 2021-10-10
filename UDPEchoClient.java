import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPEchoClient {
    public final static int serverPort = 7;
    public static void main(String[] args){
        try{
            DatagramSocket ds = new DatagramSocket();
            InetAddress server = InetAddress.getByName("localhost");
            while(true){
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                String theString = br.readLine();
                byte [] data = theString.getBytes();
                DatagramPacket dp = new DatagramPacket(data, data.length,server,serverPort);
                ds.send(dp);
                byte[] buffer = new byte [6000];
                DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
                ds.receive(incoming);
                System.out.println(new String(incoming.getData(),0,incoming.getLength()));
            }
        }catch (IOException ie){
            System.out.println(ie);
        }
    }
}