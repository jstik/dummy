module.exports = {
    makeCounter: function () {
        function counter() {
            return counter.currentCount++;
        }
        ;
        counter.currentCount = 1;
        return counter;
    },
    createHeroName: function () {
        return 'HeroName';
    }
};
//# sourceMappingURL=twst.js.map