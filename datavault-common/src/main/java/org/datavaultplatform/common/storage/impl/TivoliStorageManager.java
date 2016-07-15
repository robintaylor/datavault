package org.datavaultplatform.common.storage.impl;

import org.datavaultplatform.common.io.Progress;
import org.datavaultplatform.common.storage.ArchiveStore;
import org.datavaultplatform.common.storage.Device;
import org.datavaultplatform.common.storage.Verify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;
import java.util.UUID;

/**
 * User: Robin Taylor
 * Date: 14/07/2016
 * Time: 11:36
 */
public class TivoliStorageManager extends Device implements ArchiveStore {

    private static final Logger logger = LoggerFactory.getLogger(TivoliStorageManager.class);

    // todo : can we change this to COPY_BACK?
    public Verify.Method verificationMethod = Verify.Method.LOCAL_ONLY;

    public TivoliStorageManager(String name, Map<String,String> config) throws Exception  {
        super(name, config);

        // Unpack the config parameters (in an implementation-specific way)
        // Actually I can't think of any parameters that we need.
    }

    @Override
    public long getUsableSpace() throws Exception {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void retrieve(String path, File working, Progress progress) throws Exception {


    }

    @Override
    public String store(String path, File working, Progress progress) throws Exception {

        // todo : monitor progress

        // Note: generate a uuid to be passed as the description. We should probably use the deposit UUID instead (do we need a specialised archive method)?
        // Just a thought - Does the filename contain the deposit uuid? Could we use that as the description?
        String randomUUIDString = UUID.randomUUID().toString();

        ProcessBuilder pb = new ProcessBuilder("dsmc", "archive", working.getAbsolutePath(), "-description=" + randomUUIDString);

        Process p = pb.start();

        // This class is already running in its own thread so it can happily pause until finished.
        p.waitFor();

        if (p.exitValue() != 0) {
            logger.info("Deposit of " + working.getName() + " failed. ");
            logger.info(p.getErrorStream().toString());
            logger.info(p.getOutputStream().toString());
            throw new Exception("Deposit of " + working.getName() + " failed. ");
        }

        return randomUUIDString;
    }

    @Override
    public Verify.Method getVerifyMethod() {
        return verificationMethod;
    }
}
