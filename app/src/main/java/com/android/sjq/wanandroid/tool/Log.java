package com.android.sjq.wanandroid.tool;

/**
 * Created by Administrator on 2016/10/13.
 */

public class Log {
    public static void print(String tag, String data) {
        StackTraceElement traceElement = getTargetStackTrace();

        android.util.Log.i(tag, "(" + traceElement.getFileName() + ":" + traceElement.getLineNumber() + ")");
        android.util.Log.i(tag, data);
//        android.util.Log.i(tag, "getMethodName-->" + traceElement.getMethodName());
//        android.util.Log.i(tag, "getClassName-->" + traceElement.getClassName());


    }

    private static StackTraceElement getTargetStackTrace() {
        StackTraceElement targetStackTrace = null;
        //代码运行到当前方法时 shouldTrace = true;
        boolean shouldTrace = false;
        StackTraceElement[] stackTraceElements =
                Thread.currentThread().getStackTrace();

        for (StackTraceElement stackTraceElement : stackTraceElements) {
//            android.util.Log.i("INFO", "ClassName---->" + stackTraceElement.getClassName() +
//                    "\n" + "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")" +
//                    "\n" + "MethodName----->" + stackTraceElement.getMethodName());
            boolean isLogMethed = stackTraceElement.getClassName().equals(Log.class.getName());
            /**
             * ClassName---->dalvik.system.VMStack
             * (VMStack.java:-2)
             * MethodName----->getThreadStackTrace
             * ClassName---->java.lang.Thread
             * (Thread.java:579)
             * MethodName----->getStackTrace
             * ClassName---->com.android.sjq.wanandroid.tool.Log
             * (Log.java:25)
             * MethodName----->getTargetStackTrace
             * ClassName---->com.android.sjq.wanandroid.tool.Log
             * (Log.java:9)
             * MethodName----->print
             * ClassName---->com.android.sjq.wanandroid.activities.MainActivity$1
             * (MainActivity.java:69)
             * MethodName----->doInBackground
             *
             * 当shouldTrace = true,isLogMethed = false
             * 即取得print（）函数上一个堆栈跟踪对象信息（后进先出）
             */
            if (shouldTrace && !isLogMethed) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethed;
        }
        return targetStackTrace;
    }
}
