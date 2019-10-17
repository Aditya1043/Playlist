import java.util.*;
import java.io.*; 
class PlayList implements Serializable 
{
	class Node implements Serializable  
	{
		int Nodeid;
		Node(Node p,Node n,int id)
		{
			Nodeid=id;
			next=n;
			prev=p;
		}
		Node next;
		Node prev;
		int totalSong = 0;
		private Song Songhead = null;
		private Song Songtail = null;
		class Song implements Serializable 
		{
			int Songid;
			Song(Song p,Song n,int id)
			{
				prev=p;
				next=n;
				Songid=id;
			}
			Song next;
			Song prev;
			int getSongid()
			{
				return Songid;
			}
			Song getNext()
			{
				return next;
			}
			Song getPrev()
			{
				return prev;
			}
			void setNext(Song s)
			{
				next=s;
			}
			void setPrev(Song s)
			{
				prev=s;
			}
		}
		void play(int Songid)
		{
			Song temp=Songhead;
			Song t=Songhead;
			while(temp!=null)
			{
				if(temp.getSongid()==Songid)
				{
					System.out.println(Songid);
					try
					{
						Thread.sleep(5000);
					}
					catch(Exception e){}
					while(true)
					{
						if(Mode==1)
						{
							while(temp!=null)
							{
								System.out.println(temp.getSongid());
								try
								{
									Thread.sleep(5000);
								}
								catch(Exception e){}
								temp=temp.getNext();
							}
							temp=Songhead;
						}
						else if(Mode==2)
						{
							System.out.println(Songid);
							try
							{
								Thread.sleep(5000);
							}
							catch(Exception e){}
						}
						else if(Mode==3)
						{
							int tot=totalSong;
							System.out.println("tot"+tot);
							
							System.out.println(t.getPrev());
							System.out.println(t.getNext());
							while(tot>1)
							{
								System.out.println("Start");
								int randomIndex = (int)(Math.random()*tot);
								System.out.println("ranind"+randomIndex);
								for(int j=0;j<randomIndex;j++)
								{
									t=t.getNext();
								}
								System.out.println(t.getSongid());
								if(t.getPrev()==null)
								{
									System.out.println("songhead");
									t=t.getNext();
									t.setPrev(null);
									System.out.println("songhead1");
								}
								else if(t.getNext()==null)
								{
									System.out.println("Songtail");
									t=t.getPrev();
									t.setNext(null);
									System.out.println("Songtail1");
								}
								else
								{
									System.out.println("else");
									System.out.println(t.getPrev());
									System.out.println(t.getNext());
									t.getPrev().setNext(t.getNext());
									t.getNext().setPrev(t.getPrev());
									System.out.println("else1");
								}
								tot--;
								while(t.getPrev()!=null)
								{
									t=t.getPrev();
								}
								try
								{
									Thread.sleep(500);
								}
								catch(Exception e){}
							} 
							System.out.println(t.getSongid());
							t=Songhead;
						}
					}
				}
				temp=temp.getNext();
			}
		}
		Node getPrev()
		{
			return prev;
		}
		Node getNext()
		{
			return next;
		}
		void setNext(Node temp)
		{
			next=temp;
		}
		void setPrev(Node temp)
		{
			prev=temp;
		}
		int getId()
		{
			return Nodeid;
		}
		void addSong(int Songid)
		{
			Song s=new Song(Songtail,null,Songid);
			if(Songhead==null)
			{
				Songhead=s;
				Songtail=s;
			}
			else
			{
				Songtail.setNext(s);
				Songtail=s;
			}
			totalSong++;
		}
		void removeSong(int Songid)
		{
			/*Song temp=Songhead;
			while(temp!=null)
			{
				if(temp.getSongid()==Songid)
				{
					if(temp==Songhead)
					{
						
					}
					else if(temp==Songtail)
					{
						
					}
					else
					{
						temp.getPrev().setNext(temp.getNext());
						temp.getNext().setPrev(temp.getPrev());
					}
					break;
				}
				temp=temp.getNext();
			}*/
			Song temp1=Songhead;
			/*while(temp1!=null)
			{
				temp1=temp1.getNext();
			}*/
			while(Songhead!=null)
			{
				System.out.println(Songhead.getSongid());
				Songhead=Songhead.getNext();
			}
			//totalSong--;
		}
	}
	int Mode=2;
	private Node head = null;   
	private Node tail = null;
	private Node curr = null;
	void addPlaylist(int id)
	{
		Node temp=new Node(tail,null,id);
		if(head==null)
		{
			head=temp;
			tail=temp;
		}
		else
		{
			tail.setNext(temp);
			tail=temp;
		}
	}
	void removePlaylist()
	{
		if(curr==head)
		{
			head=head.getNext();
			head.setPrev(null);
		}
		else if(curr==tail)
		{
			tail=tail.getPrev();
			tail.setNext(null);
		}
		else
		{
			curr.getPrev().setNext(curr.getNext());
			curr.getNext().setPrev(curr.getPrev());
		}
		curr=null;
	}
	void setCurr(int id)
	{
		Node temp=head;
		while(temp!=null)
		{
			if(temp.getId()==id)
			{
				curr=temp;
				break;
			}
			temp=temp.getNext();
		}
	}
	void display()
	{
		Node temp=head;
		while(temp!=null)
		{
			System.out.println(temp.getId());
			
			temp=temp.getNext();
		}
	}
	void addSong(int Songid)
	{
		curr.addSong(Songid);
	}
	void removeSong(int Songid)
	{
		curr.removeSong(Songid);
	}
	void play(int Songid)
	{
		curr.play(Songid);
	}
	void changeMode(int Mode)
	{
		this.Mode=Mode;
	}
}
class Music
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		PlayList pl=new PlayList();
		try
        {    
            FileInputStream fis=new FileInputStream("save.dat"); 
            ObjectInputStream ois=new ObjectInputStream(fis); 
            pl=(PlayList)ois.readObject(); 
            ois.close(); 
            fis.close(); 
        } 
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
		System.out.println("1. Create playlist");
		System.out.println("2. Open playlist");
		pl.display();
		int p=sc.nextInt();
		if(p==1)
		{
			System.out.println("Enter id");
			int id=sc.nextInt();
			pl.addPlaylist(id);
		}
		else if(p==2)
		{
			System.out.println("Please enter id of playlist to open");
			int id=sc.nextInt();
			pl.setCurr(id);
			System.out.println("1. Add Song");
			System.out.println("2. Remove Song");
			System.out.println("3. Play Song");
			System.out.println("4. Change Mode");
			System.out.println("5. Delete Playlist");
			int s=sc.nextInt();
			if(s==1)
			{
				System.out.println("Please Enter id");
				int Songid=sc.nextInt();
				pl.addSong(Songid);
			}
			if(s==2)
			{
				System.out.println("Please Enter id");
				int Songid=sc.nextInt();
				pl.removeSong(Songid);
			}
			if(s==3)
			{
				System.out.println("Please Enter id");
				int Songid=sc.nextInt();
				pl.play(Songid);
			}
			if(s==4)
			{
				System.out.println("Please Enter new Mode");
				int Mode=sc.nextInt();
				pl.changeMode(Mode);
			}
			if(s==5)
			{
				pl.removePlaylist();
			}
		}
		try
        {
			FileOutputStream fos=new FileOutputStream("save.dat");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(pl);
			oos.flush();
			oos.close();
			fos.close();
		}
		catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
	}
}