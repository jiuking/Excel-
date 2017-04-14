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
		// 准备连接服务器的会话信息 
	    Properties props = new Properties(); 
	    props.setProperty("mail.store.protocol", "imap"); 
	    props.setProperty("mail.imap.host", "imap.bravowhale.com"); 
	    props.setProperty("mail.imap.port", "143"); 
	    
	    
	    props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.setProperty("mail.imap.socketFactory.fallback", "false");
	    props.setProperty("mail.imap.starttls.enable","true");
	    props.setProperty("mail.imap.socketFactory.port", "993");
	     
	    // 创建Session实例对象 
	    Session session = Session.getInstance(props); 
	     
	    // 创建IMAP协议的Store对象 
	    Store store = session.getStore("imap"); 
	     
	    // 连接邮件服务器  finshine_service@bravowhale.com
//	    store.connect("huangjingcheng@bravowhale.com", "DfJ2ndo4kE");
	    store.connect("finshine_service@bravowhale.com", "ws7Wfe6hde");
	    // 获得收件箱 
	    Folder folder = store.getFolder("INBOX"); 
	    // 以读写模式打开收件箱 
	    folder.open(Folder.READ_ONLY); 
	     
	    // 获得收件箱的邮件列表 
	    Message[] messages = folder.getMessages(); 
	    
	    // 打印不同状态的邮件数量 
	    System.out.println("收件箱中共" + messages.length + "封邮件!"); 
	    System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!"); 
	    System.out.println("收件箱中共" + folder.getNewMessageCount() + "封新邮件!"); 
	    System.out.println("收件箱中共" + folder.getDeletedMessageCount() + "封已删除邮件!"); 
	    
	    System.out.println("------------------------开始解析邮件----------------------------------"); 
//	    Message[] notReadMessages = folder.getMessages(folder.getMessageCount()-folder.getUnreadMessageCount()+1,folder.getMessageCount());
	    
	    Object[] notReadMessages = findNotReadMessages(messages);//找出未读邮件
	    System.out.println("未读邮件"+notReadMessages.length);
//	    messages = notReadMessages;
	    // 解析邮件 
	    int n = 0;
	    for (Object message : notReadMessages) { 
	    	 
	    	System.out.println(n++);
	        IMAPMessage msg = (IMAPMessage) message; 
	        String mailSubject = MimeUtility.decodeText(msg.getSubject()); 
	        System.out.println(mailSubject);
	        if(mailSubject.equals("Undelivered Mail Returned to Sender")){//无效邮件时，更新数据库信息
		        Multipart o = (Multipart) msg.getContent();
		        System.out.println(o.getBodyPart(0).getContent());
		        String mailContent = o.getBodyPart(0).getContent().toString();
		        
		        int start = mailContent.lastIndexOf("<");
		        int end = mailContent.lastIndexOf(">");
		        System.out.println(start+"----"+end);//397开始
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
	        if(flag.length == 0)//未读
	        	result.add(message);
		}
		return result.toArray();
	}

}
