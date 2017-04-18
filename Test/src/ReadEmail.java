import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.sun.mail.util.MailSSLSocketFactory;

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
	    props.setProperty("mail.imap.starttls.enable","false");
	    props.setProperty("mail.imap.socketFactory.port", "993");
	     
	    // ����Sessionʵ������ 
	    Session session = Session.getInstance(props); 
	     
	    // ����IMAPЭ���Store���� 
	    Store store = session.getStore("imap"); 
	     
	    // �����ʼ�������  finshine_service@bravowhale.com
	    store.connect("huangjingcheng@bravowhale.com", "DfJ2ndo4kE");
//	    store.connect("finshine_service@bravowhale.com", "ws7Wfe6hde");
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
	        if(flag.length == 0){//δ��
	        	result.add(message);
	        	System.out.println("δ���ʼ�");
	        }
		}
		return result.toArray();
	}
	
	public void main(){//���������⣬���������ϱ������⣬��֤�鲻���ڣ����ٷ��ĵ�������취 javax.mail ������1.5.6�汾��
		Map<String, String> mailMap = new HashMap<String, String>();
        String config = "host:imap.bravowhale.com,port:993,username:huangjingcheng@bravowhale.com,password:DfJ2ndo4kE,useSSL:true";
        for (String pair : config.split(",")) {
            String[] kv = pair.split(":");
            mailMap.put(kv[0], kv[1]);
        }

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.imap.host", mailMap.get("host"));
        props.setProperty("mail.imap.port", "143");

        props.setProperty("mail.imap.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.imap.socketFactory.fallback", "false");
        props.setProperty("mail.imap.starttls.enable", "true");
        props.setProperty("mail.imap.socketFactory.port", mailMap.get("port"));
        MailSSLSocketFactory sf;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true); 
            props.put("mail.imap.ssl.trust", "*");
            props.put("mail.imap.ssl.socketFactory", sf);
        } catch (GeneralSecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // ����Sessionʵ������
        Session session = Session.getInstance(props);
        try {
            // ����IMAPЭ���Store����
            Store store = session.getStore("imap");

            // �����ʼ������� finshine_service@bravowhale.com
            store.connect(mailMap.get("username"), mailMap.get("password"));

            // store.connect("finshine_service@bravowhale.com", "ws7Wfe6hde");
            // ����ռ���
            Folder folder = store.getFolder("INBOX");
            // �Զ�дģʽ���ռ���
            folder.open(Folder.READ_WRITE);

            // ����ռ�����ʼ��б�
            Message[] messages = folder.getMessages();

            // ��ӡ��ͬ״̬���ʼ�����

            System.out.println("------------------------��ʼ�����ʼ�----------------------------------");
            // Message[] notReadMessages =
            // folder.getMessages(folder.getMessageCount()-folder.getUnreadMessageCount()+1,folder.getMessageCount());

            // �����ʼ�
            Object[] recentMessages = findRecentMessages(messages) ;
            for (Object message : recentMessages) {
              try{
                IMAPMessage msg = (IMAPMessage) message;
                
                String mailSubject = MimeUtility.decodeText(msg.getSubject());
                System.out.println("mailSubject:" + mailSubject);
                if (mailSubject.equals("Undelivered Mail Returned to Sender")) {// ��Ч�ʼ�ʱ���������ݿ���Ϣ
                    Multipart o = (Multipart) msg.getContent();
                    System.out.println(o.getBodyPart(0).getContent());
                    String mailContent = o.getBodyPart(0).getContent()
                            .toString();

                    int start = mailContent.indexOf("<");
                    int end = mailContent.indexOf(">",start);
                    // System.out.println(start+"----"+end);//397��ʼ
                    // System.out.println(mailContent.substring(start+1,end));
                    String email = mailContent.substring(start + 1, end);
                    System.out.println("���Ᵽ�շ����ʼ���ַʧ�ܣ�email��" + email);
                    /*List<SendEmailStatus> sendEmailStatuss = sendEmailStatusDao
                            .selectByEmail(email);
                    for (SendEmailStatus sendEmailStatus : sendEmailStatuss) {
                        sendEmailStatus.setStatus((byte) 2);
                        sendEmailStatusDao.updateByPrimaryKey(sendEmailStatus);
                        // System.out.println(mailContent.substring(start+1,end).contains("@"));
                        LogUtil.info(SendEmailStatus.class,
                                "���Ᵽ�շ����ʼ���ַ���±�״̬��email��" + email);
                    }*/
                    System.out.println(mailContent.substring(start+1,end).contains("@"));
                }
              }catch(Exception e){
                  System.out.println(e.getMessage());
              }
            }
        } catch (Exception e) {
        	System.out.println( "���Ᵽ�շ����ʼ���ַ���±�״̬ʧ�ܣ�email��" + e.getMessage());
        }
    }

    private Object[] findRecentMessages(Message[] messages) throws Exception {
        // TODO Auto-generated method stub
        List<Message> result = new ArrayList<>();
        if(null!=messages && messages.length>0){
            int lastIndex = messages.length-1;
            int count =0;
            for(int i=lastIndex;i>=0;i--){
                result.add(messages[i]);
                count++;
                if(count>=100){
                    break;
                }
            }
        }
   
        return result.toArray();
    }
}
