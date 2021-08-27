const N = parseInt(readline());
out: for (let i = 0; i < N; i++) {
    const equation = readline().split("=");
    let ans = +equation[1]
    let eq = equation[0]
    try {
        if (eval(eq) == ans) {
            console.log(eq + "=" + ans)
            continue
        }
    }
    catch {}
    for (let start = 0; start < eq.length; start++) {
        for (let end = start + 2; end <= eq.length; end++) {
            let s = eq.substring(0, start) + "(" + eq.substring(start, end) + ")" + eq.substring(end)
            let n = ans + 1
            try {
                n = eval(s)
            }
            catch { }
            if (n == ans) {
                console.log(s + "=" + ans)
                continue out
            }
        }
    }
}
