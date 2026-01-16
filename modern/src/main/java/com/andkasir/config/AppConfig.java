package com.andkasir.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
    
    @Bean
    @ConfigurationProperties(prefix = "app")
    public AppProperties appProperties() {
        return new AppProperties();
    }
    
    public static class AppProperties {
        private String name;
        private String version;
        private PdfProperties pdf;
        private BackupProperties backup;
        private UiProperties ui;
        
        // Getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getVersion() { return version; }
        public void setVersion(String version) { this.version = version; }
        
        public PdfProperties getPdf() { return pdf; }
        public void setPdf(PdfProperties pdf) { this.pdf = pdf; }
        
        public BackupProperties getBackup() { return backup; }
        public void setBackup(BackupProperties backup) { this.backup = backup; }
        
        public UiProperties getUi() { return ui; }
        public void setUi(UiProperties ui) { this.ui = ui; }
    }
    
    public static class PdfProperties {
        private int fontSize = 12;
        private int margin = 20;
        
        public int getFontSize() { return fontSize; }
        public void setFontSize(int fontSize) { this.fontSize = fontSize; }
        
        public int getMargin() { return margin; }
        public void setMargin(int margin) { this.margin = margin; }
    }
    
    public static class BackupProperties {
        private boolean enabled = true;
        private String schedule = "0 0 2 * * ?";
        private String location = "./backups";
        
        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }
        
        public String getSchedule() { return schedule; }
        public void setSchedule(String schedule) { this.schedule = schedule; }
        
        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }
    }
    
    public static class UiProperties {
        private String theme = "dark";
        private int fontSize = 14;
        
        public String getTheme() { return theme; }
        public void setTheme(String theme) { this.theme = theme; }
        
        public int getFontSize() { return fontSize; }
        public void setFontSize(int fontSize) { this.fontSize = fontSize; }
    }
}