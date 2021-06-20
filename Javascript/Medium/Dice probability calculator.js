const s = readline()
let variables = s.replace(/[()]/g, "").split(/[-+*//=<>]/);
let dir = {}
let permutations = []
let die = []
for (const v of variables) {
    if (v.charAt(0) == 'd') {
        const nums = []
        for (let i = 1; i <= +v.substr(1); i++) {
            nums.push(i)
        }
        dir[v] = nums
        die.push(v)
    }
}

const ans = {}
let max = 0
let min = 10000
let total = 0

perm(permutations, die, dir, 0, s)

for (const p of permutations) {
    const n = eval(p)
    const k = m => isNaN(m) ? 1 : m + 1

    if (n == true)
        ans[1] = k(ans[1])
    else if (n == false)
        ans[0] = k(ans[0])
    else if (!isNaN(n))
        ans[n] = k(ans[n])

    max = Math.max(max, n)
    min = Math.min(min, n)
    total++
}

for (let i = min; i <= max; i++) {
    if (!isNaN(ans[i])) {
        const N = (ans[i] / total) * 100
        console.log(i + " " + N.toFixed(2))
    }
}

function perm(permutations, die, dir, index, s) {
    if (index == die.length) {
        permutations.push(s)
        return
    }
    for (const n of dir[die[index]]) {
        const str = s.replace(die[index], n)
        perm(permutations, die, dir, index + 1, str)
    }
}
