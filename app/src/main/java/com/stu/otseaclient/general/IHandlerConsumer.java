package com.stu.otseaclient.general;

import android.content.Context;
import android.os.Message;

public interface IHandlerConsumer {
    void handle(Context curText, Message msg);
}
