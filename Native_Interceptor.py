import sys
import frida

Hook_package = "com.example.internandroid"

def on_message(message, data):
    print("{} -> {}".format(message, data))

jscode = """
    console.log("[*] Starting script");
    Interceptor.attach(Module.findExportByName("libinternandroid.so", "Java_com_example_internandroid_ComposeActivity2Kt_printValueLength"), {
        //var len = 0;
        onEnter: function (arg0, arg1, arg2) {
            var len = arg.length
        },
        onLeave: function (retval) {
            console.log(retval);
            retval.replace(len);
            console.log(retval);
        }
    });
"""

try:
    device = frida.get_device("emulator-5554", timeout=10) #실행
    pid = device.spawn([Hook_package]) #프로세스 실행 및 pid 확인
    print("App is starting.. pid:{}".format(pid))
    process = device.attach(pid)
    device.resume(pid)
    script = process.create_script(jscode) #연결에 성공하면 스크립트를 생성하고 로드
    script.on('message', on_message)
    print("[-] Running FRIDA!")
    script.load()
    sys.stdin.read() #자바스크립트가 동작하지 전에 코드가 종료하는 것을 방지

except Exception as e:
    print(e)