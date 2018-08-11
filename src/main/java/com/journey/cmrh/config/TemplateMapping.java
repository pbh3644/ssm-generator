package com.journey.cmrh.config;

/**
 * 模板映射
 *
 * @author
 */
public class TemplateMapping {

    private String template;
    private String dir;
    private String suffix = "java";        // default java
    private String project;
    private String packagePath;            // default calc from dir
    private String entityPackage;       //default calc from dir
    private String ePadding = "";        // padding the end of file name
    private String sPadding = "";        // padding the start of file name

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
