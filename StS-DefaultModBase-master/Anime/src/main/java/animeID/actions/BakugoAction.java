package animeID.actions;

import animeID.cards.CommonAttacks.Bakugo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
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

public class BakugoAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int[] multidamage;
    public BakugoAction(final AbstractPlayer p, final int[] multidamage,
                        final DamageInfo.DamageType damageTypeForTurn){
        this.p = p;
        this.multidamage=multidamage;
        this.actionType = ActionType.DAMAGE;
        this.damageType = damageTypeForTurn;
    }

    @Override
    public void update() {

        boolean playedMusic = false;
        int l = AbstractDungeon.getCurrRoom().monsters.monsters.size();

        for(int i = 0; i < l; ++i) {
            if (!((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isDying && ((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).currentHealth > 0 && !((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isEscaping) {
                if (playedMusic) {
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cX, ((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cY, this.attackEffect, true));
                } else {
                    playedMusic = true;
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cX, ((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cY, this.attackEffect));
                }
            }
        }

        Iterator var5 = AbstractDungeon.player.powers.iterator();

        while(var5.hasNext()) {
            AbstractPower p = (AbstractPower)var5.next();
            p.onDamageAllEnemies(this.multidamage);
        }


        for(int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
            AbstractMonster target = (AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if (!target.isDying && target.currentHealth > 0 && !target.isEscaping) {
                DamageInfo info =new DamageInfo(this.source, this.multidamage[i], this.damageType);
                target.damage(info);
                if ( info.output >target.currentHealth) {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Bakugo(true), 1));
                }
            }
        }


            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        isDone = true;
    }
}

