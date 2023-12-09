-injars       java-juggling.jar
-outjars      Main0.jar
-libraryjars  <java.home>/jmods/java.base.jmod(!**.jar;!module-info.class)
-optimizationpasses 10
-optimizeaggressively

-keep public class Main {
    public static void main(java.lang.String[]);
}