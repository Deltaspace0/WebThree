function updateClock() {
    const doubleDigit = value => (value < 10) ? "0" + value.toString() : value.toString();
    const time      = new Date(Date.now());
    const date      = doubleDigit(time.getDate());
    const month     = doubleDigit(time.getMonth()+1);
    const year      = time.getFullYear();
    const hours     = doubleDigit(time.getHours());
    const minutes   = doubleDigit(time.getMinutes());
    const seconds   = doubleDigit(time.getSeconds());
    const str = `${date}.${month}.${year} ${hours}:${minutes}:${seconds}`;
    document.getElementById("clock").innerHTML = str
}