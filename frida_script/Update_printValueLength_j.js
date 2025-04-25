console.log("[*] Starting script");
const ModuleName = "libinternandroid.so";
const MethodName = "Java_com_example_internandroid_ComposeActivity2Kt_printValueLength";
Interceptor.attach(Module.findExportByName(ModuleName, MethodName), {
    onEnter: function (args) {
        console.log("[*]Hooked function called!");
        console.log("args[2]: ", args[2]);
        var str
        var ptr_prompt = args[2];
        Java.perform(function() {
            var String = Java.use("java.lang.String");
            str = Java.cast(ptr(ptr_prompt), String);
        });
        console.log("str: ", str);
        this.len = String(str).length;
    },
    onLeave: function (retval) {
        console.log(retval);
        retval.replace(this.len);
        console.log(retval);
    }
});