import { TitleProps } from '../../interface'
import * as styles from './Title.styles' 
 
 export function Title({title}: TitleProps) {
  return (
    <div style={styles.titleContainer}>{title}</div>
  )
}