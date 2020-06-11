function _createForOfIteratorHelper(o) { if (typeof Symbol === "undefined" || o[Symbol.iterator] == null) { if (Array.isArray(o) || (o = _unsupportedIterableToArray(o))) { var i = 0; var F = function F() {}; return { s: F, n: function n() { if (i >= o.length) return { done: true }; return { done: false, value: o[i++] }; }, e: function e(_e) { throw _e; }, f: F }; } throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method."); } var it, normalCompletion = true, didErr = false, err; return { s: function s() { it = o[Symbol.iterator](); }, n: function n() { var step = it.next(); normalCompletion = step.done; return step; }, e: function e(_e2) { didErr = true; err = _e2; }, f: function f() { try { if (!normalCompletion && it.return != null) it.return(); } finally { if (didErr) throw err; } } }; }

function _unsupportedIterableToArray(o, minLen) { if (!o) return; if (typeof o === "string") return _arrayLikeToArray(o, minLen); var n = Object.prototype.toString.call(o).slice(8, -1); if (n === "Object" && o.constructor) n = o.constructor.name; if (n === "Map" || n === "Set") return Array.from(o); if (n === "Arguments" || /^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)) return _arrayLikeToArray(o, minLen); }

function _arrayLikeToArray(arr, len) { if (len == null || len > arr.length) len = arr.length; for (var i = 0, arr2 = new Array(len); i < len; i++) { arr2[i] = arr[i]; } return arr2; }

// Inspired By
// https://codepen.io/abeatrize/pen/LJqYey
// Bongo Cat originally created by @StrayRogue and @DitzyFlama
var ID = "bongo-cat";

var s = function s(selector) {
    return "#".concat(ID, " ").concat(selector);
};

var notes = document.querySelectorAll(".note");

var _iterator = _createForOfIteratorHelper(notes),
    _step;

try {
    for (_iterator.s(); !(_step = _iterator.n()).done;) {
        var note = _step.value;
        note.parentElement.appendChild(note.cloneNode(true));
        note.parentElement.appendChild(note.cloneNode(true));
    }
} catch (err) {
    _iterator.e(err);
} finally {
    _iterator.f();
}

var music = {
    note: s(".music .note")
};
var terminal = {
    frame: s(".terminal-frame"),
    code: s(".terminal-code line")
};
var cat = {
    pawRight: {
        up: s(".paw-right .up"),
        down: s(".paw-right .down")
    },
    pawLeft: {
        up: s(".paw-left .up"),
        down: s(".paw-left .down")
    }
};
var style = getComputedStyle(document.documentElement);
var green = style.getPropertyValue("--green");
var pink = style.getPropertyValue("--pink");
var blue = style.getPropertyValue("--blue");
var orange = style.getPropertyValue("--orange");
var cyan = style.getPropertyValue("--cyan");
gsap.set(music.note, {
    scale: 0,
    autoAlpha: 1
});

var animatePawState = function animatePawState(selector) {
    return gsap.fromTo(selector, {
        autoAlpha: 0
    }, {
        autoAlpha: 1,
        duration: 0.01,
        repeatDelay: 0.19,
        yoyo: true,
        repeat: -1
    });
};

var tl = gsap.timeline();
tl.add(animatePawState(cat.pawLeft.up), "start").add(animatePawState(cat.pawRight.down), "start").add(animatePawState(cat.pawLeft.down), "start+=0.19").add(animatePawState(cat.pawRight.up), "start+=0.19").timeScale(1.6);
gsap.from(".terminal-code line", {
    drawSVG: "0%",
    duration: 0.1,
    stagger: 0.1,
    ease: 'none',
    repeat: -1
});
var noteEls = gsap.utils.pipe(gsap.utils.toArray, gsap.utils.shuffle)(music.note);
var numNotes = noteEls.length / 3;
var notesG1 = noteEls.splice(0, numNotes);
var notesG2 = noteEls.splice(0, numNotes);
var notesG3 = noteEls;
var colorizer = gsap.utils.random([green, pink, blue, orange, cyan, "#a3a4ec", "#67b5c0", "#fd7c6e"], true);
var rotator = gsap.utils.random(-50, 50, 1, true);

var dir = function dir(amt) {
    return "".concat(gsap.utils.random(["-", "+"]), "=").concat(amt);
};

var animateNotes = function animateNotes(els) {
    els.forEach(function (el) {
        gsap.set(el, {
            stroke: colorizer(),
            rotation: rotator(),
            x: gsap.utils.random(-25, 25, 1)
        });
    });
    return gsap.fromTo(els, {
        autoAlpha: 1,
        y: 0,
        scale: 0
    }, {
        duration: 2,
        autoAlpha: 0,
        scale: 1,
        ease: "none",
        stagger: {
            from: "random",
            each: 0.5
        },
        rotation: dir(gsap.utils.random(20, 30, 1)),
        x: dir(gsap.utils.random(40, 60, 1)),
        y: gsap.utils.random(-200, -220, 1),
        onComplete: function onComplete() {
            return animateNotes(els);
        }
    });
};

tl.add(animateNotes(notesG1)).add(animateNotes(notesG2), ">0.05").add(animateNotes(notesG3), ">0.25");