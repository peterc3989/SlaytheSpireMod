package animeID.actions;

import animeID.cards.CommonAttacks.Bakugo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import animeID.powers.CommonPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.FlyingOrbEffect;

import java.util.Iterator;

public class EraserHeadAction extends AbstractGameAction {
    private AbstractPlayer p;
    public EraserHeadAction(final AbstractPlayer p){
        this.p = p;
        this.actionType = ActionType.DAMAGE;
    }

    @Override
    public void update() {



        for(int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
            AbstractMonster target = (AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            Iterator var5 = target.powers.iterator();

            while(var5.hasNext()) {
                AbstractPower p = (AbstractPower)var5.next();
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(target,this.p, p));
            }
        }



        isDone = true;
    }
}

