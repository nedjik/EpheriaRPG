package special.rpgplugin.data;

public enum PlayerClass {
    WARRIOR {
        @Override
        public int getStartStrength() {
            return 15;
        }

        @Override
        public int getStartConstitution() {
            return 15;
        }

        @Override
        public int getStartDexterity() {
            return 5;
        }

        @Override
        public int getStartIntelligence() {
            return 10;
        }

        @Override
        public String getSpellListForActionBar() {
            return "[1] - Выпад [2] - Крик [3] - Боевой клич";
        }


    },
    RANGER {
        @Override
        public int getStartStrength() {
            return 5;
        }

        @Override
        public int getStartConstitution() {
            return 10;
        }

        @Override
        public int getStartDexterity() {
            return 15;
        }

        @Override
        public int getStartIntelligence() {
            return 10;
        }

        @Override
        public String getSpellListForActionBar() {
            return "[1] - Рывок [2] - Град стрел [3] - Взрывная стрела";
        }
    },
    MAGE {
        @Override
        public int getStartStrength() {
            return 5;
        }

        @Override
        public int getStartConstitution() {
            return 10;
        }

        @Override
        public int getStartDexterity() {
            return 10;
        }

        @Override
        public int getStartIntelligence() {
            return 15;
        }

        @Override
        public String getSpellListForActionBar() {
            return "[1] - Магический снаряд [2] - Фаерболл [3] - Самолечение";
        }
    };

    // Абстрактные методы для получения характеристик
    public abstract int getStartStrength();
    public abstract int getStartConstitution();
    public abstract int getStartDexterity();
    public abstract int getStartIntelligence();
    public abstract String getSpellListForActionBar();
}

