/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.overlord.commons.config.configurator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Reads the configuration from the User Home directory.
 * 
 * @author David Virgil Naranjo
 */
public class UserHomeConfigurator extends AbstractPropertiesFileConfigurator {

    /**
     * Constructor.
     */
    public UserHomeConfigurator() {
    }
    
    /**
     * @see org.overlord.commons.config.configurator.Configurator#accept()
     */
    @Override
    public boolean accept() {
        return true;
    }
    
    /**
     * @see org.overlord.commons.config.configurator.AbstractPropertiesFileConfigurator#findConfigUrl(java.lang.String)
     */
    @Override
    protected URL findConfigUrl(String configName) {
        String userHomeDir = System.getProperty("user.home"); //$NON-NLS-1$
        if (userHomeDir != null) {
            File dirFile = new File(userHomeDir);
            if (dirFile.isDirectory()) {
                File cfile = new File(dirFile, configName);
                if (cfile.isFile()) {
                    try {
                        return cfile.toURI().toURL();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return null;
    }
}
