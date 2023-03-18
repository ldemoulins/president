object Logger {
    private var debug = false
    fun activateDebugLogger() {
        debug = true
        log("Debug mode activated");
    }

    fun log(message: String) {
        if(debug) {
            println("[DEBUG] $message")
        }
    }
}