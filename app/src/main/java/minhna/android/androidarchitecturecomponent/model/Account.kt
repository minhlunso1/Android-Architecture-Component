package minhna.android.androidarchitecturecomponent.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by minhnguyen on 12/1/17.
 */

data class Account(val id: Int, val cardNumber: Long) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readLong()
    )

  override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeLong(cardNumber)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Account> = object : Parcelable.Creator<Account> {
            override fun createFromParcel(source: Parcel): Account = Account(source)
            override fun newArray(size: Int): Array<Account?> = arrayOfNulls(size)
        }
    }

    fun getFormatCardNumber(): String {
        val cardString : String = cardNumber.toString()
        var returnString: String = ""
        var flag = 0
        for (i in 0..cardString.length - 1) {
            returnString += cardString.get(i)
            if (flag == 3 && i < cardString.length - 1) {
                returnString += "-"
                flag = -1
            }
            flag++
        }
        return returnString
    }
}