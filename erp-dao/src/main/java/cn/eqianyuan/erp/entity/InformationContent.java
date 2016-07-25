package cn.eqianyuan.erp.entity;

public class InformationContent {

    private Integer organizationId;
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 链接地址url
     */
    private String urladdress;

    /**
     *指令
     */
    private String instructions;

    /**
     * 提交方式
     */
    private String submission;

    /**
     *参数
     */
    private String parameter;

    /**
     *响应内容
     */
    private String responsecontent;

    /**
     *错误日志
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