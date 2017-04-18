import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class ShowEmail {  
  
    private MimeMessage mimeMessage = null;  
    private String saveAttachPath = ""; // �������غ�Ĵ��Ŀ¼  
    private StringBuffer bodyText = new StringBuffer(); // ����ʼ����ݵ�StringBuffer����  
    private String dateFormat = "yy-MM-dd HH:mm"; // Ĭ�ϵ���ǰ��ʾ��ʽ  
  
    /**  
     * ���캯��,��ʼ��һ��MimeMessage����  
     */  
    public ShowEmail() {  
    }  
  
    public ShowEmail(MimeMessage mimeMessage) {  
        this.mimeMessage = mimeMessage;  
        System.out.println("����һ��ReceiveEmail����....");  
    }  
  
    public void setMimeMessage(MimeMessage mimeMessage) {  
        this.mimeMessage = mimeMessage;  
        System.out.println("����һ��MimeMessage����...");  
    }  
  
    /**  
     * ��*����÷����˵ĵ�ַ������ ��  
     */  
    public String getFrom() throws Exception {  
        InternetAddress address[] = (InternetAddress[]) mimeMessage.getFrom();  
        String from = address[0].getAddress();  
        if (from == null) {  
            from = "";  
            System.out.println("�޷�֪��������.");  
        }  
        String personal = address[0].getPersonal();  
  
        if (personal == null) {  
            personal = "";  
            System.out.println("�޷�֪�������ߵ�����.");  
        }  
  
        String fromAddr = null;  
        if (personal != null || from != null) {  
            fromAddr = personal + "<" + from + ">";  
            System.out.println("�������ǣ�" + fromAddr);  
        } else {  
            System.out.println("�޷���÷�������Ϣ.");  
        }  
        return fromAddr;  
    }  
  
    /**  
     * ��*������ʼ����ռ��ˣ����ͣ������͵ĵ�ַ�����������������ݵĲ����Ĳ�ͬ  
     * ��*��"to"----�ռ��ˡ�"cc"---�����˵�ַ��"bcc"---�����˵�ַ ��  
     */  
    public String getMailAddress(String type) throws Exception {  
        String mailAddr = "";  
        String addType = type.toUpperCase();  
  
        InternetAddress[] address = null;  
        if (addType.equals("TO") || addType.equals("CC")  
                || addType.equals("BCC")) {  
  
            if (addType.equals("TO")) {  
                address = (InternetAddress[]) mimeMessage  
                        .getRecipients(Message.RecipientType.TO);  
            } else if (addType.equals("CC")) {  
                address = (InternetAddress[]) mimeMessage  
                        .getRecipients(Message.RecipientType.CC);  
            } else {  
                address = (InternetAddress[]) mimeMessage  
                        .getRecipients(Message.RecipientType.BCC);  
            }  
  
            if (address != null) {  
                for (int i = 0; i < address.length; i++) {  
                    String emailAddr = address[i].getAddress();  
                    if (emailAddr == null) {  
                        emailAddr = "";  
                    } else {  
                        System.out.println("ת��֮ǰ��emailAddr: " + emailAddr);  
                        emailAddr = MimeUtility.decodeText(emailAddr);  
                        System.out.println("ת��֮���emailAddr: " + emailAddr);  
                    }  
                    String personal = address[i].getPersonal();  
                    if (personal == null) {  
                        personal = "";  
                    } else {  
                        System.out.println("ת��֮ǰ��personal: " + personal);  
                        personal = MimeUtility.decodeText(personal);  
                        System.out.println("ת��֮���personal: " + personal);  
                    }  
                    String compositeto = personal + "<" + emailAddr + ">";  
                    System.out.println("�������ʼ���ַ��" + compositeto);  
                    mailAddr += "," + compositeto;  
                }  
                mailAddr = mailAddr.substring(1);  
            }  
        } else {  
            throw new Exception("����ĵ����ʼ�����!");  
        }  
        return mailAddr;  
    }  
  
    /**  
     * ��*������ʼ����� ��  
     */  
    public String getSubject() throws MessagingException {  
        String subject = "";  
        try {  
            System.out.println("ת��ǰ��subject��" + mimeMessage.getSubject());  
            subject = MimeUtility.decodeText(mimeMessage.getSubject());  
            System.out.println("ת�����subject: " + mimeMessage.getSubject());  
            if (subject == null) {  
                subject = "";  
            }  
        } catch (Exception exce) {  
            exce.printStackTrace();  
        }  
        return subject;  
    }  
  
    /**  
     * ��*������ʼ��������� ��  
     */  
    public String getSentDate() throws Exception {  
        Date sentDate = mimeMessage.getSentDate();  
        System.out.println("�������� ԭʼ����: " + dateFormat);  
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);  
        String strSentDate = format.format(sentDate);  
        System.out.println("�������� �ɶ�����: " + strSentDate);  
        return strSentDate;  
    }  
  
    /**  
     * ��*������ʼ��������� ��  
     */  
    public String getBodyText() {  
        return bodyText.toString();  
    }  
  
    /**  
     * ����*�������ʼ����ѵõ����ʼ����ݱ��浽һ��StringBuffer�����У������ʼ�  
     * ����*����Ҫ�Ǹ���MimeType���͵Ĳ�ִͬ�в�ͬ�Ĳ�����һ��һ���Ľ��� ����  
     */  
  
    public void getMailContent(Part part) throws Exception {  
  
        String contentType = part.getContentType();  
        // ����ʼ���MimeType����  
        System.out.println("�ʼ���MimeType����: " + contentType);  
  
        int nameIndex = contentType.indexOf("name");  
  
        boolean conName = false;  
  
        if (nameIndex != -1) {  
            conName = true;  
        }  
  
        System.out.println("�ʼ����ݵ�����:��" + contentType);  
  
        if (part.isMimeType("text/plain") && conName == false) {  
            // text/plain ����  
            bodyText.append((String) part.getContent());  
        } else if (part.isMimeType("text/html") && conName == false) {  
            // text/html ����  
            bodyText.append((String) part.getContent());  
        } else if (part.isMimeType("multipart/*")) {  
            // multipart/*  
            Multipart multipart = (Multipart) part.getContent();  
            int counts = multipart.getCount();  
            for (int i = 0; i < counts; i++) {  
                getMailContent(multipart.getBodyPart(i));  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            // message/rfc822  
            getMailContent((Part) part.getContent());  
        } else {  
  
        }  
    }  
  
    /**  
     * ����*���жϴ��ʼ��Ƿ���Ҫ��ִ�������Ҫ��ִ����"true",���򷵻�"false" ��  
     */  
    public boolean getReplySign() throws MessagingException {  
  
        boolean replySign = false;  
  
        String needReply[] = mimeMessage  
                .getHeader("Disposition-Notification-To");  
  
        if (needReply != null) {  
            replySign = true;  
        }  
        if (replySign) {  
            System.out.println("���ʼ���Ҫ�ظ�");  
        } else {  
            System.out.println("���ʼ�����Ҫ�ظ�");  
        }  
        return replySign;  
    }  
  
    /**  
     *����ô��ʼ���Message-ID ����  
     */  
    public String getMessageId() throws MessagingException {  
        String messageID = mimeMessage.getMessageID();  
        System.out.println("�ʼ�ID: " + messageID);  
        return messageID;  
    }  
  
    /**  
     * �жϴ��ʼ��Ƿ��Ѷ������δ������false,��֮����true  
     */  
    public boolean isNew() throws MessagingException {  
        boolean isNew = false;  
        Flags flags = ((Message) mimeMessage).getFlags();  
        Flags.Flag[] flag = flags.getSystemFlags();  
        System.out.println("flags�ĳ���:��" + flag.length);  
        for (int i = 0; i < flag.length; i++) {  
            if (flag[i] == Flags.Flag.SEEN) {  
                isNew = true;  
                System.out.println("seen email...");  
                // break;  
            }  
        }  
        return isNew;  
    }  
  
    /**  
     * �жϴ��ʼ��Ƿ��������  
     */  
    public boolean isContainAttach(Part part) throws Exception {  
        boolean attachFlag = false;  
        // String contentType = part.getContentType();  
        if (part.isMimeType("multipart/*")) {  
            Multipart mp = (Multipart) part.getContent();  
            for (int i = 0; i < mp.getCount(); i++) {  
                BodyPart mPart = mp.getBodyPart(i);  
                String disposition = mPart.getDisposition();  
                if ((disposition != null)  
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition  
                                .equals(Part.INLINE))))  
                    attachFlag = true;  
                else if (mPart.isMimeType("multipart/*")) {  
                    attachFlag = isContainAttach((Part) mPart);  
                } else {  
                    String conType = mPart.getContentType();  
  
                    if (conType.toLowerCase().indexOf("application") != -1)  
                        attachFlag = true;  
                    if (conType.toLowerCase().indexOf("name") != -1)  
                        attachFlag = true;  
                }  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            attachFlag = isContainAttach((Part) part.getContent());  
        }  
        return attachFlag;  
    }  
  
    /**  
     * ��*�����渽�� ��  
     */  
  
    public void saveAttachMent(Part part) throws Exception {  
        String fileName = "";  
        if (part.isMimeType("multipart/*")) {  
            Multipart mp = (Multipart) part.getContent();  
            for (int i = 0; i < mp.getCount(); i++) {  
                BodyPart mPart = mp.getBodyPart(i);  
                String disposition = mPart.getDisposition();  
                if ((disposition != null)  
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition  
                                .equals(Part.INLINE)))) {  
                    fileName = mPart.getFileName();  
                    if (fileName.toLowerCase().indexOf("gb2312") != -1) {  
                        fileName = MimeUtility.decodeText(fileName);  
                    }  
                    saveFile(fileName, mPart.getInputStream());  
                } else if (mPart.isMimeType("multipart/*")) {  
                    saveAttachMent(mPart);  
                } else {  
                    fileName = mPart.getFileName();  
                    if ((fileName != null)  
                            && (fileName.toLowerCase().indexOf("GB2312") != -1)) {  
                        fileName = MimeUtility.decodeText(fileName);  
                        saveFile(fileName, mPart.getInputStream());  
                    }  
                }  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            saveAttachMent((Part) part.getContent());  
        }  
    }  
  
    /**  
     *�����ø������·��  
     */  
    public void setAttachPath(String attachPath) {  
        this.saveAttachPath = attachPath;  
    }  
  
    /**  
     * ��*������������ʾ��ʽ ��  
     */  
    public void setDateFormat(String format) throws Exception {  
        this.dateFormat = format;  
    }  
  
    /**  
     * ��*����ø������·�� ��  
     */  
    public String getAttachPath() {  
        return saveAttachPath;  
    }  
  
    /**  
     * ��*�������ı��渽����ָ��Ŀ¼�� ��  
     */  
    private void saveFile(String fileName, InputStream in) throws Exception {  
        String osName = System.getProperty("os.name");  
        String storeDir = getAttachPath();  
        String separator = "";  
        if (osName == null) {  
            osName = "";  
        }  
        if (osName.toLowerCase().indexOf("win") != -1) {  
            separator = "\\";  
            if (storeDir == null || storeDir.equals(""))  
                storeDir = "c:\\tmp";  
        } else {  
            separator = "/";  
            storeDir = "/tmp";  
        }  
        File storeFile = new File(storeDir + separator + fileName);  
        System.out.println("�����ı����ַ:��" + storeFile.toString());  
        // for(int��i=0;storefile.exists();i++){  
        // storefile��=��new��File(storedir+separator+fileName+i);  
        // }  
        BufferedOutputStream bos = null;  
        BufferedInputStream bis = null;  
  
        try {  
            bos = new BufferedOutputStream(new FileOutputStream(storeFile));  
            bis = new BufferedInputStream(in);  
            int c;  
            while ((c = bis.read()) != -1) {  
                bos.write(c);  
                bos.flush();  
            }  
        } catch (Exception exception) {  
            exception.printStackTrace();  
            throw new Exception("�ļ�����ʧ��!");  
        } finally {  
            bos.close();  
            bis.close();  
        }  
    }  
  
    /**  
     *��ReceiveEmail�����  
     */  
    public static void main(String args[]) throws Exception {  
        String host = "imap.bravowhale.com";  
        String username = "huangjingcheng@bravowhale.com";  
        String password = "DfJ2ndo4kE";  
  
        Properties props = new Properties();  
        Session session = Session.getDefaultInstance(props, null);  
  
        Store store = session.getStore("imap");  
        store.connect(host, username, password);  
  
        Folder folder = store.getFolder("INBOX");  
        folder.open(Folder.READ_ONLY);  
        Message message[] = folder.getMessages();  
        System.out.println("�ʼ�����:��" + message.length);  
        ShowEmail re = null;  
  
        for (int i = 0; i < message.length; i++) {  
            re = new ShowEmail((MimeMessage) message[i]);  
            System.out.println("�ʼ���" + i + "������:��" + re.getSubject());  
            System.out.println("�ʼ���" + i + "������ʱ��:��" + re.getSentDate());  
            System.out.println("�ʼ���" + i + "���Ƿ���Ҫ�ظ�:��" + re.getReplySign());  
            System.out.println("�ʼ���" + i + "���Ƿ��Ѷ�:��" + re.isNew());  
            System.out.println("�ʼ���" + i + "���Ƿ��������:��"  
                    + re.isContainAttach((Part) message[i]));  
            System.out.println("�ʼ���" + i + "�������˵�ַ:��" + re.getFrom());  
            System.out  
                    .println("�ʼ���" + i + "�������˵�ַ:��" + re.getMailAddress("to"));  
            System.out.println("�ʼ���" + i + "������:��" + re.getMailAddress("cc"));  
            System.out.println("�ʼ���" + i + "������:��" + re.getMailAddress("bcc"));  
            re.setDateFormat("yy��MM��dd�ա�HH:mm");  
            System.out.println("�ʼ���" + i + "������ʱ��:��" + re.getSentDate());  
            System.out.println("�ʼ���" + i + "���ʼ�ID:��" + re.getMessageId());  
            re.getMailContent((Part) message[i]);  
            System.out.println("�ʼ���" + i + "����������:��\r\n" + re.getBodyText());  
            re.setAttachPath("e:\\");  
            re.saveAttachMent((Part) message[i]);  
        }  
    }  
}  
