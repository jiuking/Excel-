package com.annotation.test;

import java.math.BigDecimal;
import java.util.Date;

public class PropertyBean {

	@Property(name = "��Ʒ����", type = PropertyType.STRING, nullable = false)
	private String productName;

	@Property(name = "��Ԫ��", type = PropertyType.STRING, nullable = false)
	@Convert(convert = AmountConvert.class)
	private BigDecimal totalAmount;

	@Property(name = "�µ�ʱ��", type = PropertyType.DATE, nullable = false)
	private Date date_orderCreate;

	@Property(name = "��������", type = PropertyType.STRING, nullable = false, regex = "^[\\u4E00-\\u9FA5A-Za-z]+$")
	private String bankName;

	@Property(name = "������", type = PropertyType.STRING, nullable = false, maxLength = 32)
	private String cardNumber;

	@Property(name = "����������ʱ��", type = PropertyType.DATE, nullable = false)
	private Date date_orderAudit;

	@Property(name = "֧����ʽ", type = PropertyType.STRING, nullable = false)
	@Convert(convert = PaymentConvert.class)
	private Integer paymentStatus;

	@Property(name = "����״̬", type = PropertyType.STRING, nullable = false)
	@Convert(convert = OrderStatusConvert.class)
	private String orderStatus;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getDate_orderCreate() {
		return date_orderCreate;
	}

	public void setDate_orderCreate(Date date_orderCreate) {
		this.date_orderCreate = date_orderCreate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getDate_orderAudit() {
		return date_orderAudit;
	}

	public void setDate_orderAudit(Date date_orderAudit) {
		this.date_orderAudit = date_orderAudit;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public static class AmountConvert implements PropertyConvert<BigDecimal> {

		@Override
		public BigDecimal convert(Object obj) throws ValueFormatException {
			String str = (String) obj;
			return new BigDecimal(str);
		}

	}

	public static class PaymentConvert implements PropertyConvert<Integer> {

		@Override
		public Integer convert(Object obj) throws ValueFormatException {
			String str = (String) obj;
			if ("����MPOS֧��".equals(str))
				return 10;
			if ("ת�˸���".equals(str))
				return 40;
			throw new ValueFormatException("��ʽ����");
		}

	}

	public static class OrderStatusConvert implements PropertyConvert<String> {

		@Override
		public String convert(Object obj) throws ValueFormatException {
			String str = (String) obj;
			if ("�����".equals(str))
				return "0";
			if ("���ͨ��".equals(str))
				return "1";
			if ("��˲�ͨ��".equals(str))
				return "2";
			throw new ValueFormatException("��ʽ����");
		}

	}

}
