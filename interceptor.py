import sys
import frida

Hook_package = "com.example.internandroid"

def on_message(message, data):
    print("{} -> {}".format(message, data))

jscode = """
    console.log("[*] Starting script");
    Java.perform(function() {
        var targetClass = Java.use("com.example.internandroid.CV");
        targetClass.compare.implementation = function(arg0, arg1) {
            console.log('[*] CV.compare function hooking Success!');
            console.log("Parameters: ", '"'+arg0+'"', '"'+arg1+'"');
            var result = arg0==arg1 ? true : false;
            console.log("Return Value: ", result);
            return Java.use("java.lang.Boolean").$new(result);
            //frida에서 반환하는 값은 오토박싱되지 않기 때문에 객체를 생성해 반환해야한다.
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