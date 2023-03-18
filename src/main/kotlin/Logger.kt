object Logger {
    private var debug = false
    fun activateDebugLogger() {
        debug = true
    }

    fun log(message: String) {
        if(debug) {
            println("[DEBUG] $message")
        }
    }
}