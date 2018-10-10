package com.pbh.journey.system.app.config;


/**
 * @author pangbohuan
 * @description 模板映射
 * @date 2018-08-20 14:03
 **/
public class TemplateMapping {

    private String template;
    private String dir;
    /**
     * calc from dir
     */
    private String suffix = "java";
    private String project;
    /**
     * default calc from dir
     */
    private String packagePath;
    private String entityPackage;
    /**
     * padding the end of file name
     */
    private String ePadding = "";
    private String sPadding = "";

    public String buildPackage(String project, String packageP, String modelName) {

        String localPackagePath = this.getPackagePath();
        System.out.println("localPackagePath:" + localPackagePath);


        if (this.getPackagePath() != null && !"".equals(this.getPackagePath())) {

            localPackagePath = localPackagePath.replaceAll("\\$\\{project\\}", project);
            localPackagePath = localPackagePath.replaceAll("\\$\\{packagePath\\}", packageP);
            localPackagePath = localPackagePath.replaceAll("\\$\\{model\\}", modelName);
            localPackagePath = localPackagePath.replaceAll("[\\/]", ".");
        }
        System.out.println("#########localPackagePath#####end:" + localPackagePath);
        return localPackagePath;
    }

    public String buildDir(String project, String packageP) {
        String localDir = getDir();

        if (this.getDir() != null && !"".equals(this.getDir())) {
            localDir = localDir.replaceAll("\\$\\{project\\}", project);
            localDir = localDir.replaceAll("\\$\\{packagePath\\}", packageP);
        }

        return localDir;
    }

    public String buildDir(String project, String packageP, String modelName) {
        String localDir = getDir();

        if (this.getDir() != null && !"".equals(this.getDir())) {
            localDir = localDir.replaceAll("\\$\\{project\\}", project);
            localDir = localDir.replaceAll("\\$\\{packagePath\\}", packageP);
            localDir = localDir.replaceAll("\\$\\{model\\}", modelName);

        }
        System.out.println("getDir:" + localDir);
        return localDir;
    }


    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getePadding() {
        return ePadding;
    }

    public void setePadding(String ePadding) {
        this.ePadding = ePadding;
    }

    public String getsPadding() {
        return sPadding;
    }

    public void setsPadding(String sPadding) {
        this.sPadding = sPadding;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }
}
