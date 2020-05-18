package animeID.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import animeID.powers.CommonPower;

public class TsuyuPowerAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private int magicNumber;
    private AbstractPlayer p;
    private boolean upgraded;

    public TsuyuPowerAction( final AbstractPlayer p,
                               final int magicNumber){
        this.freeToPlayOnce = false;
        this.p = p;
        this.magicNumber = magicNumber;
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        if(p.hand.size()>=3)
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
        isDone = true;
    }
}
