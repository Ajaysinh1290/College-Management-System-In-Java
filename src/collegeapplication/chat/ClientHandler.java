package collegeapplication.chat;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable
{
	
	Socket socket;
	ObjectInputStream reader;
	ObjectOutputStream writer;
	int position=0;
	public ClientHandler(Socket socket)
	{
		try
		{
		this.socket=socket;
		this.reader=new ObjectInputStream(socket.getInputStream());
		this.writer=new ObjectOutputStream(socket.getOutputStream());
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		try
		{
			while(true)
			{	
						ChatUser user=(ChatUser)reader.readObject();
						for(ClientHandler s:Server.clientlist)
						{	
								System.out.println(user.getFromUserId()+":"+user.getMessage());
								s.writer.writeObject(user);
								s.writer.reset();
						}
			}
		}
		catch(EOFException exp)
		{
			Server.clientlist.remove(this.position);
			int i=0;
			for(ClientHandler s:Server.clientlist)
			{
				s.position=i;
				i++;
			}
		}
		catch(SocketException exp)
		{
			//if user is disconnected than close input stream and output stream
			Server.clientlist.remove(this.position);
			int i=0;
			for(ClientHandler s:Server.clientlist)
			{
				s.position=i;
				i++;
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
	}


}


