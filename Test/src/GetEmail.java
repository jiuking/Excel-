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
     
    // 连接邮件服务器 
    store.connect("huangjingcheng@bravowhale.com", "DfJ2ndo4kE");
    // 获得收件箱 
    Folder folder = store.getFolder("INBOX"); 
    // 以读写模式打开收件箱 
    folder.open(Folder.READ_WRITE); 
     
    // 获得收件箱的邮件列表 
    Message[] messages = folder.getMessages(); 
     
    // 打印不同状态的邮件数量 
    System.out.println("收件箱中共" + messages.length + "封邮件!"); 
    System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!"); 
    System.out.println("收件箱中共" + folder.getNewMessageCount() + "封新邮件!"); 
    System.out.println("收件箱中共" + folder.getDeletedMessageCount() + "封已删除邮件!"); 
     
    System.out.println("------------------------开始解析邮件----------------------------------"); 
    Message[] noReadMessages = folder.getMessages(folder.getMessageCount()-folder.getUnreadMessageCount()+1,folder.getMessageCount());
    System.out.println("未读邮件"+noReadMessages.length);
    messages = noReadMessages;
    // 解析邮件 
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
	        System.out.println("发现附件1: " + (part.getFileName() != null ?  MimeUtility.decodeText(part.getFileName()):"null"));
        }
        if(o instanceof Multipart){
        	 Multipart multipart = (Multipart) o ;  
             reMultipart(multipart); 
        }
//        System.out.println(part.isMimeType("multipart/*")+"----"+subject);
//        System.out.println(part.getDisposition()+"----");
//        System.out.println("发现附件: " + (part.getFileName() != null ?  MimeUtility.decodeText(part.getFileName()):"null"));
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
        	System.out.println("[" + subject + "]未读，是否需要阅读此邮件（yes/no）？"+i); 
        }
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
//        String answer = reader.readLine();  
//        if ("yes".equalsIgnoreCase(answer)) { 
////        	POP3ReceiveMailTest.parseMessage(msg);  // 解析邮件 
//            // 第二个参数如果设置为true，则将修改反馈给服务器。false则不反馈给服务器 
//            msg.setFlag(Flag.SEEN, true);   //设置已读标志 
//        } 
    } 
     
    // 关闭资源 
    folder.close(false); 
    store.close(); 
} 
	 /** 
     * @param part 解析内容 
     * @throws Exception 
     */  
    private static void rePart(Part part) throws MessagingException,  
            UnsupportedEncodingException, IOException, FileNotFoundException {  
        if (part.getDisposition() != null) {  
          
            String strFileNmae = MimeUtility.decodeText(part.getFileName()); //MimeUtility.decodeText解决附件名乱码问题  
            System.out.println("发现附件: " +  MimeUtility.decodeText(part.getFileName()));  
            System.out.println("内容类型: " + MimeUtility.decodeText(part.getContentType()));  
//            System.out.println("附件内容:" + part.getContent());  
            /*InputStream in = part.getInputStream();// 打开附件的输入流  
            // 读取附件字节并存储到文件中  
            java.io.FileOutputStream out = new FileOutputStream(strFileNmae);  
            int data;  
            while((data = in.read()) != -1) {  
                out.write(data);  
            }  
            in.close();  
            out.close();  */
        } else {  
            if(part.getContentType().startsWith("text/plain")) {  
                System.out.println("文本内容：" + part.getContent());  
            }else if(part.getContent() instanceof IMAPInputStream){  
                System.out.println("HTML内容1：" + ((IMAPInputStream)part.getContent()));  
            } 
            else if(part.getContent() instanceof IMAPNestedMessage){  
                System.out.println("HTML内容2：" + ((MimeMultipart)((IMAPNestedMessage)part.getContent()).getContent()).getBodyPart(0).getContent());
                System.out.println("HTML内容2：" + ((MimeMultipart)((IMAPNestedMessage)part.getContent()).getContent()).getBodyPart(1).getContent());
            }  else{
            	System.out.println("HTML内容：" + (part.getContent()));  
            }
        }  
    }  
      
    /** 
     * @param multipart // 接卸包裹（含所有邮件内容(包裹+正文+附件)） 
     * @throws Exception 
     */  
    private static void reMultipart(Multipart multipart) throws Exception {  
        System.out.println("邮件共有" + multipart.getCount() + "部分组成");  
        // 依次处理各个部分  
        for (int j = 0, n = multipart.getCount(); j < n; j++) {  
            System.out.println("处理第" + j + "部分");  
            Part part = multipart.getBodyPart(j);//解包, 取出 MultiPart的各个部分, 每部分可能是邮件内容,  
            // 也可能是另一个小包裹(MultipPart)  
            // 判断此包裹内容是不是一个小包裹, 一般这一部分是 正文 Content-Type: multipart/alternative  
            if (part.getContent() instanceof Multipart) {  
                Multipart p = (Multipart) part.getContent();// 转成小包裹  
                //递归迭代  
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
