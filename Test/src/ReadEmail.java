import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeUtility;

import com.sun.mail.imap.IMAPMessage;

public class ReadEmail {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// ׼�����ӷ������ĻỰ��Ϣ 
	    Properties props = new Properties(); 
	    props.setProperty("mail.store.protocol", "imap"); 
	    props.setProperty("mail.imap.host", "imap.bravowhale.com"); 
	    props.setProperty("mail.imap.port", "143"); 
	    
	    
	    props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.setProperty("mail.imap.socketFactory.fallback", "false");
	    props.setProperty("mail.imap.starttls.enable","true");
	    props.setProperty("mail.imap.socketFactory.port", "993");
	     
	    // ����Sessionʵ������ 
	    Session session = Session.getInstance(props); 
	     
	    // ����IMAPЭ���Store���� 
	    Store store = session.getStore("imap"); 
	     
	    // �����ʼ�������  finshine_service@bravowhale.com
//	    store.connect("huangjingcheng@bravowhale.com", "DfJ2ndo4kE");
	    store.connect("finshine_service@bravowhale.com", "ws7Wfe6hde");
	    // ����ռ��� 
	    Folder folder = store.getFolder("INBOX"); 
	    // �Զ�дģʽ���ռ��� 
	    folder.open(Folder.READ_ONLY); 
	     
	    // ����ռ�����ʼ��б� 
	    Message[] messages = folder.getMessages(); 
	    
	    // ��ӡ��ͬ״̬���ʼ����� 
	    System.out.println("�ռ����й�" + messages.length + "���ʼ�!"); 
	    System.out.println("�ռ����й�" + folder.getUnreadMessageCount() + "��δ���ʼ�!"); 
	    System.out.println("�ռ����й�" + folder.getNewMessageCount() + "�����ʼ�!"); 
	    System.out.println("�ռ����й�" + folder.getDeletedMessageCount() + "����ɾ���ʼ�!"); 
	    
	    System.out.println("------------------------��ʼ�����ʼ�----------------------------------"); 
//	    Message[] notReadMessages = folder.getMessages(folder.getMessageCount()-folder.getUnreadMessageCount()+1,folder.getMessageCount());
	    
	    Object[] notReadMessages = findNotReadMessages(messages);//�ҳ�δ���ʼ�
	    System.out.println("δ���ʼ�"+notReadMessages.length);
//	    messages = notReadMessages;
	    // �����ʼ� 
	    int n = 0;
	    for (Object message : notReadMessages) { 
	    	 
	    	System.out.println(n++);
	        IMAPMessage msg = (IMAPMessage) message; 
	        String mailSubject = MimeUtility.decodeText(msg.getSubject()); 
	        System.out.println(mailSubject);
	        if(mailSubject.equals("Undelivered Mail Returned to Sender")){//��Ч�ʼ�ʱ���������ݿ���Ϣ
		        Multipart o = (Multipart) msg.getContent();
		        System.out.println(o.getBodyPart(0).getContent());
		        String mailContent = o.getBodyPart(0).getContent().toString();
		        
		        int start = mailContent.lastIndexOf("<");
		        int end = mailContent.lastIndexOf(">");
		        System.out.println(start+"----"+end);//397��ʼ
		        System.out.println(mailContent.substring(start+1,end));
		        System.out.println(mailContent.substring(start+1,end).contains("@"));
	        }
	    }
	}

	private static Object[] findNotReadMessages(Message[] messages) throws Exception {
		// TODO Auto-generated method stub
		List<Message> result = new ArrayList<>();
		for(Message message : messages){
			Flags flags = message.getFlags();     
	        Flags.Flag[] flag = flags.getSystemFlags();     
	        System.out.println("flags's length: " + flag.length);     
	        if(flag.length == 0)//δ��
	        	result.add(message);
		}
		return result.toArray();
	}

}
