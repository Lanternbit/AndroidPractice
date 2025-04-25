const openPtr = Module.getExportByName(ModuleName, 'a');
// const open = new NativeFunction(openPtr, 'int', ['int']);
Interceptor.replace(openPtr, new NativeCallback(function(value) {
    console.log("\noriginal value: ", value);
    value = 0;
    console.log("new value: ", value);
    return value;
}, 'int', ['int'])
);