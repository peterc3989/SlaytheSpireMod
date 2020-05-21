package animeID.actions;

import animeID.cards.CommonAttacks.Bakugo;
import animeID.cards.UncommonAttacks.HowitzerImpact;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
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

public class HowitzerImpactAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private int magicNumber;
    private int damage;
    private AbstractPlayer p;
    private AbstractMonster m;
    private int energyOnUse;
    private boolean upgraded;
    public HowitzerImpactAction(final AbstractPlayer p, final AbstractMonster m,final int damage,
                                final DamageInfo.DamageType damageTypeForTurn) {
        this.freeToPlayOnce = false;
        this.p = p;
        this.m=m;
        this.damageType = damageTypeForTurn;
        this.damage=damage;
    }

    @Override
    public void update() {


            if (!m.isDying && m.currentHealth > 0 && !m.isEscaping) {
                int curr = m.currentHealth+m.currentBlock;
                DamageInfo info =new DamageInfo(this.p, this.damage, this.damageType);
                m.damage(info);
                if ( info.output >curr) {
                    AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));
                }
            }



        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
        isDone = true;
    }
}

