package com.stu.otseaclient.component.image;

import android.content.Context;
import android.util.AttributeSet;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.stu.otseaclient.general.GeneralHandle;

import java.io.File;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/22 15:45
 * @Description:
 */
public class NetImageView extends androidx.appcompat.widget.AppCompatImageView {
    public static final int IMAGE_NET_THREAD_POOL_SIZE = 3;
    private static ImageLoader imageLoader;

    static {
        imageLoader = ImageLoader.getInstance();
        Context context = GeneralHandle.getInstance().getCurContext();


        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        File cacheDir = StorageUtils.getCacheDirectory(context);
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
//                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
//                .diskCacheExtraOptions(480, 800, null)
//                .taskExecutor(Executors.newFixedThreadPool(IMAGE_NET_THREAD_POOL_SIZE))
//                .taskExecutorForCachedImages(Executors.newFixedThreadPool(IMAGE_NET_THREAD_POOL_SIZE))
//                .threadPoolSize(3) // default
//                .threadPriority(Thread.NORM_PRIORITY - 2) // default
//                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
//                .denyCacheImageMultipleSizesInMemory()
//                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
//                .memoryCacheSize(2 * 1024 * 1024)
//                .memoryCacheSizePercentage(13) // default
//                .diskCache(new UnlimitedDiskCache(cacheDir)) // default
//                .diskCacheSize(50 * 1024 * 1024)
//                .diskCacheFileCount(100)
//                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
//                .imageDownloader(new BaseImageDownloader(context)) // default
//                .imageDecoder(new BaseImageDecoder(false)) // default
//                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
//                .writeDebugLogs()
//                .build();
        imageLoader.init(config.build());
    }

    public NetImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NetImageView(Context context) {
        super(context);
    }

    public NetImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置网络图片
     *
     * @param path
     */
    public void setImageURL(String path) {

        imageLoader.displayImage(path, this);
    }
}
