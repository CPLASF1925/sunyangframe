package com.bril.base.net;


import com.bril.base.net.down.FileConverter;

import java.io.File;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

/**
 * Created by 123456 on 2017/8/9.
 */

public interface DownloadService {
    @GET
    Call<File> download(@Url String fileUrl, @Header(FileConverter.SAVE_PATH) String path);
}
