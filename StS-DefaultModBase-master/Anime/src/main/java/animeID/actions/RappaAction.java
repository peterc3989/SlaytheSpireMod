package animeID.actions;

import animeID.cards.CommonAttacks.Bakugo;
import animeID.cards.UncommonAttacks.Rappa;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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

public class RappaAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractMonster m;
    private int damage;
    public RappaAction(final AbstractPlayer p, final AbstractMonster m,final int damage,
                        final DamageInfo.DamageType damageTypeForTurn){
        this.p = p;
        this.m =m;
        this.damage = damage;
        this.actionType = ActionType.DAMAGE;
        this.damageType = damageTypeForTurn;
    }

    @Override
    public void update() {
        DamageInfo info =new DamageInfo(this.p, this.damage, this.damageType);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, info, AttackEffect.SMASH));


                if ( m.currentBlock>info.output) {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Rappa(true), 1));
                }


        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
        isDone = true;
    }
}

