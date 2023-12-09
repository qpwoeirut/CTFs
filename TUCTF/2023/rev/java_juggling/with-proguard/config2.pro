-injars       Main1.class
-outjars      Main2.jar
-libraryjars  <java.home>/jmods/java.base.jmod(!**.jar;!module-info.class)
-optimizationpasses 10
-optimizeaggressively

-keep public class Main1 {
    public static void main(java.lang.String[]);
}