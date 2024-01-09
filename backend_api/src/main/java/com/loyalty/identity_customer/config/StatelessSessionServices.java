//package com.loyalty.topic1.config;
//
//import org.kie.api.KieServices;
//import org.kie.api.builder.KieBuilder;
//import org.kie.api.builder.KieFileSystem;
//import org.kie.api.builder.KieModule;
//import org.kie.api.command.Command;
//import org.kie.api.io.ResourceType;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.StatelessKieSession;
//import org.kie.internal.command.CommandFactory;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//public class StatelessSessionServices<T> {
//    private final KieServices kieServices;
//    private final KieContainer kContainer;
//    private StatelessKieSession kieSession = null;
//    private KieFileSystem kieFileSystem = null;
//
//    public StatelessSessionServices(List<String> ruleFilePaths) {
//        kieServices = KieServices.Factory.get();
//        kContainer = getKieContainer(ruleFilePaths);
//    }
//
//    public KieContainer getKieContainer(List<String> ruleFilePaths) {
//        for (String path : ruleFilePaths) {
//            File file = new File(path);
//            org.kie.api.io.Resource resource = kieServices.getResources().newFileSystemResource(file).setResourceType(ResourceType.DRL);
//            if (kieFileSystem == null) {
//                kieFileSystem = kieServices.newKieFileSystem();
//            }
//            kieFileSystem.write(resource);
//        }
//        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
//        kb.buildAll();
//        KieModule kieModule = kb.getKieModule();
//
//        return kieServices.newKieContainer(kieModule.getReleaseId());
//    }
//
//    public void execute(T obj) {
//        StatelessKieSession kSession = getKieSession();
//        kSession.execute(obj);
//    }
//    public void batchExecute(List<T> lstObj) {
//        StatelessKieSession kSession = getKieSession();
//
//        List<Command> lstCms = new ArrayList<>();
//        for (T obj : lstObj) {
//            lstCms.add(CommandFactory.newInsert(obj));
//        }
//        kSession.execute(CommandFactory.newBatchExecution(lstCms));
//    }
//
//    public StatelessKieSession getKieSession() {
//        if (kieSession == null) {
//            kieSession = kContainer.newStatelessKieSession();
//        }
//        return kieSession;
//    }
//
//    public void setGlobalVariable(String id, Object value) {
//        StatelessKieSession session = getKieSession();
//        session.setGlobal(id, value);
//    }
//}
