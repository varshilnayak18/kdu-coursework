import { CardProps } from "../../interface";
import * as styles from './Card.styles' 

export function Card({type, hotel, selectedHotel, setSelectedHotel}: CardProps) {
  return (
    <div style={hotel === selectedHotel? styles.cardSelected : styles.card} onClick={() => setSelectedHotel(hotel)}>
        {type}
    </div>
  )
}