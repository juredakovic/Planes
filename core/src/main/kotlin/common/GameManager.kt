package common

object GameManager {

    private  var packages : Int = 0
    private  var lifes : Int = 3
    private var birdSpeed : Float = 100f
    private var opponentPlaneSpeed : Float = 100f
    private var packageSpeed : Float = 100f
    private var birdFrequency : Float = 1f
    private var opponentPlaneFrequency : Float = 3f
    private var packageFrequency : Float = 3f

    fun getBirdSpeed() : Float {
        return birdSpeed
    }

    fun getPackageSpeed() : Float {
        return packageSpeed
    }

    fun getOpponentPlaneSpeed() : Float {
        return opponentPlaneSpeed
    }

    fun setDifficulty(difficulty : String){

        when{(difficulty == "easy") -> {
            birdFrequency = 1f
            packageFrequency = 3f
            opponentPlaneFrequency = 3f
            lifes = 3
            birdSpeed = 100f
            opponentPlaneSpeed = 100f
        }}

        when{(difficulty == "medium") -> {
            birdFrequency = 1f
            packageFrequency = 2f
            opponentPlaneFrequency = 1.5f
            lifes = 2
            birdSpeed = 300f
            opponentPlaneSpeed = 300f
            packageSpeed = 200f
        }}

        when{(difficulty == "hard") -> {
            birdFrequency = 0.5f
            packageFrequency = 1f
            opponentPlaneFrequency = 1f
            lifes = 1
            birdSpeed = 400f
            opponentPlaneSpeed = 400f
            packageSpeed = 300f
        }}
    }

    fun getBirdFrequency() : Float {
        return birdFrequency
    }

    fun getOpponentPlaneFrequency() : Float {
        return opponentPlaneFrequency
    }

    fun getPackageFrequency() : Float {
        return packageFrequency
    }

    fun getLives() : Int {
        return lifes
    }

    fun getPackages() : Int {
        return packages
    }

    fun resetResult() {
        packages = 0
        lifes = this.lifes
    }

    fun isGameOver() : Boolean {
        if(lifes <= 0) {
            return true
        }
            return false
    }

    fun pickPackage() {
        packages++
    }

    fun damage() {
        lifes--
    }

    fun damageBig() {
        lifes = 0
    }

}
