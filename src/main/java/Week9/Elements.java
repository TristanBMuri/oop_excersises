package Week9;

public enum Elements {
    Lead(5, 6, 7),
    Iron(5, 6, 7),
    Water(5, 6, 7);

    final private float solid;
    final private float liquid;
    final private float gas;

    Elements(float solid, float liquid, float gas) {
        this.solid = solid;
        this.liquid = liquid;
        this.gas = gas;
    }

    public float getGas() {
        return gas;
    }

    public float getLiquid() {
        return liquid;
    }

    public float getSolid() {
        return solid;
    }

    public String getState(float temperature){
        if (-1 ==Float.compare(getGas(), temperature)) {
            return "GAS";
        } else if (-1 ==Float.compare(getLiquid(), temperature)) {
            return "LIQUID";
        }
        else {
            return "SOLID";
        }
    }
}
