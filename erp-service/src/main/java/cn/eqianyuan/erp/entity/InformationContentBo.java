package cn.eqianyuan.erp.entity;

/**
 * Created by asus on 2016/7/11.
 */
public class InformationContentBo {

    private Integer organizationId;
    /**
     * ����ID
     */
    private Integer id;

    /**
     * ���ӵ�ַurl
     */
    private String urladdress;

    /**
     *ָ��
     */
    private String instructions;

    /**
     * �ύ��ʽ
     */
    private String submission;

    /**
     *����
     */
    private String parameter;

    /**
     *��Ӧ����
     */
    private String responsecontent;

    /**
     *������־
     */
    private String errorreporting;

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrladdress() {
        return urladdress;
    }

    public void setUrladdress(String urladdress) {
        this.urladdress = urladdress;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getSubmission() {
        return submission;
    }

    public void setSubmission(String submission) {
        this.submission = submission;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getResponsecontent() {
        return responsecontent;
    }

    public void setResponsecontent(String responsecontent) {
        this.responsecontent = responsecontent;
    }

    public String getErrorreporting() {
        return errorreporting;
    }

    public void setErrorreporting(String errorreporting) {
        this.errorreporting = errorreporting;
    }
}
