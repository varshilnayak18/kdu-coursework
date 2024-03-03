import { useState } from "react";
import * as styles from './AddOn.styles'
import { AddOnProps, IAddOn } from "../../interface";

export function AddOn({addOn, selectedAddOns, setSelectedAddOns}: AddOnProps) {
    const [selectedAddOn, setSelectedAddOn] = useState<boolean>(false);
    const handleSelect = () => {
        if(selectedAddOn){
            setSelectedAddOns(selectedAddOns.filter((addOnn: IAddOn)=> addOnn !== addOn))
            setSelectedAddOn(false);
        }
        else{
            setSelectedAddOns([...selectedAddOns, addOn]);
            setSelectedAddOn(true);
        }
    }
  return (
    <div
      style={selectedAddOn ? styles.selectedAddOn : styles.addOn}
      onClick={handleSelect}
    >
      {addOn.name}
    </div>
  );
}
