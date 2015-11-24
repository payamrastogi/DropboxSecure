package com.secure.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.DbxFiles.FileMetadata;
import com.dropbox.core.v2.DbxFiles.Metadata;
import com.dropbox.core.v2.DbxUsers.FullAccount;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.junit.Test;

public class TestDropboxAPI 
{
	@Test
	public void testDropboxAPI() throws DbxException, IOException
	{
		String ACCESS_TOKEN = "y0lkcwDOwhEAAAAAAAAbYT-n62-V-pPvGEnmEoBHcSwVoTsYmma8tzxqOsqAIExF";
		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
        FullAccount account = client.users.getCurrentAccount();
        System.out.println(account.name.displayName);

        // Get files and folder metadata from Dropbox root directory
        ArrayList<Metadata> entries = client.files.listFolder("").entries;
        for (Metadata metadata : entries) {
            System.out.println(metadata.pathLower);
        }

        // Upload "test.txt" to Dropbox
        InputStream in = new FileInputStream("src/test/resources/test.txt");
        FileMetadata metadata = client.files.uploadBuilder("/test.txt").run(in);
	}
}
