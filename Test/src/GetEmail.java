import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.sun.mail.imap.IMAPInputStream;
import com.sun.mail.imap.IMAPMessage;
import com.sun.mail.imap.IMAPNestedMessage;

public class GetEmail {
	public static void main(String[] args) throws Exception{
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
     
    // �����ʼ������� 
    store.connect("huangjingcheng@bravowhale.com", "DfJ2ndo4kE");
    // ����ռ��� 
    Folder folder = store.getFolder("INBOX"); 
    // �Զ�дģʽ���ռ��� 
    folder.open(Folder.READ_WRITE); 
     
    // ����ռ�����ʼ��б� 
    Message[] messages = folder.getMessages(); 
     
    // ��ӡ��ͬ״̬���ʼ����� 
    System.out.println("�ռ����й�" + messages.length + "���ʼ�!"); 
    System.out.println("�ռ����й�" + folder.getUnreadMessageCount() + "��δ���ʼ�!"); 
    System.out.println("�ռ����й�" + folder.getNewMessageCount() + "�����ʼ�!"); 
    System.out.println("�ռ����й�" + folder.getDeletedMessageCount() + "����ɾ���ʼ�!"); 
     
    System.out.println("------------------------��ʼ�����ʼ�----------------------------------"); 
    Message[] noReadMessages = folder.getMessages(folder.getMessageCount()-folder.getUnreadMessageCount()+1,folder.getMessageCount());
    System.out.println("δ���ʼ�"+noReadMessages.length);
    messages = noReadMessages;
    // �����ʼ� 
    int n = 0;
    for (Message message : messages) { 
    	 
    	System.out.println(n++);
        IMAPMessage msg = (IMAPMessage) message; 
        String subject = MimeUtility.decodeText(msg.getSubject()); 
        Object o = msg.getContent();
        if (o instanceof Part){  
            Part part = (Part) o;  
            System.out.println(part.isMimeType("multipart/*")+"----"+subject);
	        System.out.println(part.getDisposition()+"----");
	        System.out.println("���ָ���1: " + (part.getFileName() != null ?  MimeUtility.decodeText(part.getFileName()):"null"));
        }
        if(o instanceof Multipart){
        	 Multipart multipart = (Multipart) o ;  
             reMultipart(multipart); 
        }
//        System.out.println(part.isMimeType("multipart/*")+"----"+subject);
//        System.out.println(part.getDisposition()+"----");
//        System.out.println("���ָ���: " + (part.getFileName() != null ?  MimeUtility.decodeText(part.getFileName()):"null"));
        boolean isnew = false;     
        Flags flags = msg.getFlags();     
        Flags.Flag[] flag = flags.getSystemFlags();     
        System.out.println("flags's length: " + flag.length);     
        for (int i = 0; i < flag.length; i++) {     
            if (flag[i] == Flags.Flag.SEEN) {     
                isnew = true;     
                System.out.println("seen Message.......");     
                break;     
            }     
        }     
        int i = 0;
        if(subject.equals("Undelivered Mail Returned to Sender")&&isContainAttach(msg)){
        	i++;
        	System.out.println("[" + subject + "]δ�����Ƿ���Ҫ�Ķ����ʼ���yes/no����"+i); 
        }
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
//        String answer = reader.readLine();  
//        if ("yes".equalsIgnoreCase(answer)) { 
////        	POP3ReceiveMailTest.parseMessage(msg);  // �����ʼ� 
//            // �ڶ��������������Ϊtrue�����޸ķ�������������false�򲻷����������� 
//            msg.setFlag(Flag.SEEN, true);   //�����Ѷ���־ 
//        } 
    } 
     
    // �ر���Դ 
    folder.close(false); 
    store.close(); 
} 
	 /** 
     * @param part �������� 
     * @throws Exception 
     */  
    private static void rePart(Part part) throws MessagingException,  
            UnsupportedEncodingException, IOException, FileNotFoundException {  
        if (part.getDisposition() != null) {  
          
            String strFileNmae = MimeUtility.decodeText(part.getFileName()); //MimeUtility.decodeText�����������������  
            System.out.println("���ָ���: " +  MimeUtility.decodeText(part.getFileName()));  
            System.out.println("��������: " + MimeUtility.decodeText(part.getContentType()));  
//            System.out.println("��������:" + part.getContent());  
            /*InputStream in = part.getInputStream();// �򿪸�����������  
            // ��ȡ�����ֽڲ��洢���ļ���  
            java.io.FileOutputStream out = new FileOutputStream(strFileNmae);  
            int data;  
            while((data = in.read()) != -1) {  
                out.write(data);  
            }  
            in.close();  
            out.close();  */
        } else {  
            if(part.getContentType().startsWith("text/plain")) {  
                System.out.println("�ı����ݣ�" + part.getContent());  
            }else if(part.getContent() instanceof IMAPInputStream){  
                System.out.println("HTML����1��" + ((IMAPInputStream)part.getContent()));  
            } 
            else if(part.getContent() instanceof IMAPNestedMessage){  
                System.out.println("HTML����2��" + ((MimeMultipart)((IMAPNestedMessage)part.getContent()).getContent()).getBodyPart(0).getContent());
                System.out.println("HTML����2��" + ((MimeMultipart)((IMAPNestedMessage)part.getContent()).getContent()).getBodyPart(1).getContent());
            }  else{
            	System.out.println("HTML���ݣ�" + (part.getContent()));  
            }
        }  
    }  
      
    /** 
     * @param multipart // ��ж�������������ʼ�����(����+����+����)�� 
     * @throws Exception 
     */  
    private static void reMultipart(Multipart multipart) throws Exception {  
        System.out.println("�ʼ�����" + multipart.getCount() + "�������");  
        // ���δ����������  
        for (int j = 0, n = multipart.getCount(); j < n; j++) {  
            System.out.println("�����" + j + "����");  
            Part part = multipart.getBodyPart(j);//���, ȡ�� MultiPart�ĸ�������, ÿ���ֿ������ʼ�����,  
            // Ҳ��������һ��С����(MultipPart)  
            // �жϴ˰��������ǲ���һ��С����, һ����һ������ ���� Content-Type: multipart/alternative  
            if (part.getContent() instanceof Multipart) {  
                Multipart p = (Multipart) part.getContent();// ת��С����  
                //�ݹ����  
                reMultipart(p);  
            } else {  
                rePart(part);  
            }  
         }  
    }  
	public static boolean isContainAttach(Part part) throws Exception {     
        boolean attachflag = false;     
        String contentType = part.getContentType();     
        if (part.isMimeType("multipart/*")) {     
            Multipart mp = (Multipart) part.getContent();     
            for (int i = 0; i < mp.getCount(); i++) {     
                BodyPart mpart = mp.getBodyPart(i);     
                String disposition = mpart.getDisposition();     
                if ((disposition != null)     
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition     
                                .equals(Part.INLINE))))     
                    attachflag = true;     
                else if (mpart.isMimeType("multipart/*")) {     
                    attachflag = isContainAttach((Part) mpart);     
                } else {     
                    String contype = mpart.getContentType();     
                    if (contype.toLowerCase().indexOf("application") != -1)     
                        attachflag = true;     
                    if (contype.toLowerCase().indexOf("name") != -1)     
                        attachflag = true;     
                }     
            }     
        } else if (part.isMimeType("message/rfc822")) {     
            attachflag = isContainAttach((Part) part.getContent());     
        }     
        return attachflag;     
    }     
}
