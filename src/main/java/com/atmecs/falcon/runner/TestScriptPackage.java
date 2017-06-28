package com.atmecs.falcon.runner;

import java.io.File;

public class TestScriptPackage {
    String pack = "";

    public String getPack() {
        return pack;
    }

    public void getPackage(File node) {
        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename : subNote) {
                getPackage(new File(node, filename));
            }
        } else if (node.isFile()) {
            if (System.getProperties().getProperty("os.name").contains("Windows")) {
                pack =
                        pack
                        + ","
                        + node.getParent().split("java" + File.separator + File.separator)[1];
            } else if (System.getProperties().getProperty("os.name").contains("Mac")) {
                pack = pack + "," + node.getParent().split("java" + File.separator)[1];
            }
            System.out.println("package is :" + pack);
        }
    }

    public void setPack(String pack) {
        this.pack = pack;
    }
}
