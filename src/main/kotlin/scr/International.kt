import java.time.LocalTime

class International : Other {
    var country: String

    constructor(costXMinutes: Double, country:String){
        this.costXMinutes = costXMinutes
        this.country = country
    }
}