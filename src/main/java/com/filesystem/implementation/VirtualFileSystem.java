package com.filesystem.implementation;

import com.filesystem.*;

public class VirtualFileSystem implements VFS {

    private static VirtualFileSystem fileSystem;
    private VirtualDirectory root;
    private Access accessManager;

    private VirtualFileSystem() {
        this.root = VirtualFileImpl.createRootDirectory("root");
        accessManager = AccessManager.getInstance();
    }

    public static VirtualFileSystem getFileSystem() {
        if (fileSystem == null) {
            fileSystem = new VirtualFileSystem();
        }
        return fileSystem;
    }

    /**
     *
     * @return root virtual directory
     */
    @Override
    public VirtualDirectory getRoot() {
        return root;
    }

    /**
     *
     * @return access manager
     */
    @Override
    public Access getAccess() {
        return accessManager;
    }

    /**
     * Search file/directory inside root directory antd its sub-directories
     * @param name file/directory name
     * @return found virtual file/directory or null
     */
    @Override
    public VFile findFile(String name) {

        return root.getFile(name);
    }

    /**
     * remove access to all files in virtual file system for user
     * @param user
     */
    @Override
    public void removeUser(UserInterface user){
        UserInterface u = accessManager.removeUser(user);
        root.removeAccess(u);
    }

}
